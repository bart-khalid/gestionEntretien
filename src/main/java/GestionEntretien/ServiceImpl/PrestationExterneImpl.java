/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.Entretien;
import GestionEntretien.Bean.LocalDetails;
import GestionEntretien.Bean.Locale;
import GestionEntretien.Bean.PresBonCommande;
import GestionEntretien.Bean.PresBonLivraison;
import GestionEntretien.Bean.PrestationExterne;
import GestionEntretien.Bean.Reclamation;
import GestionEntretien.Dao.EntretienRepository;
import GestionEntretien.Dao.LocalDetailsRepository;
import GestionEntretien.Dao.LocaleRepository;
import GestionEntretien.Dao.PresBonCommandeRepository;
import GestionEntretien.Dao.PresBonLivraisonRepository;
import GestionEntretien.Dao.PrestationExterneRepository;
import GestionEntretien.Dao.ReclamationRepository;
import GestionEntretien.Service.PrestationExterneService;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class PrestationExterneImpl implements PrestationExterneService {

    @Autowired
    private PrestationExterneRepository prestationExterneRepository;
    @Autowired
    private ReclamationRepository reclamationRepository;
    @Autowired
    private PresBonCommandeRepository presBonCommandeRepository;
    @Autowired
    private PresBonLivraisonRepository presBonLivraisonRepository;
    @Autowired
    private LocaleRepository localeRepository;
    @Autowired
    private LocalDetailsRepository localDetailsRepository;
    @Autowired
    private EntretienRepository entretienRepository;

    @Override
    public int save(PrestationExterne prestationExterne) {
        if (prestationExterne.getLocale().getReference() == null) {
            return -1;
        } else if (prestationExterne.getMaterielLocale().getReferenceML() == null && prestationExterne.getTypeEntretienE().equals("materiel")) {
            return -2;
        } else if (prestationExterne.isReclamedE() && prestationExterne.getReclamationE().getReference() == null) {
            return -3;
        } else if (prestationExterne.isBonCommandeE()
                && (prestationExterne.getPresBonCommandeE().getNomPrestataireC() == null
                || prestationExterne.getPresBonCommandeE().getNumeroBonCommande() == null
                || prestationExterne.getPresBonCommandeE().getDateBonCommande() == null)) {
            return -4;
        } else if (prestationExterne.isBonLivraisonE()
                && (prestationExterne.getPresBonLivraisonE().getNomPrestataireL() == null
                || prestationExterne.getPresBonLivraisonE().getNumeroBonLivraison() == null
                || prestationExterne.getPresBonLivraisonE().getDateBonLivraison() == null)) {
            return -5;
        } else {
            // generate a unique random reference
            PrestationExterne.setNbrPresExterne(PrestationExterne.getNbrPresExterne() + 1);
            prestationExterne.setReferenceE(RandomStringUtils.random(6, true, false) + String.valueOf(PrestationExterne.getNbrPresExterne()));
            PrestationExterne foundedPresExterne = prestationExterneRepository.findByReferenceE(prestationExterne.getReferenceE());
            while (foundedPresExterne != null) {
                prestationExterne.setReferenceE(RandomStringUtils.random(6, true, false) + String.valueOf(PrestationExterne.getNbrPresExterne()));
                foundedPresExterne = prestationExterneRepository.findByReferenceE(prestationExterne.getReferenceE());
            }

            // update liste pres du locale
            Locale loadedLocale = localeRepository.findByReference(prestationExterne.getLocale().getReference());
            List<PrestationExterne> prestations = loadedLocale.getPrestationsE();
            prestations.add(prestationExterne);
            loadedLocale.setPrestationsE(prestations);
            localeRepository.save(loadedLocale);
            // set
            prestationExterne.setNomLocale(loadedLocale.getDescriptionDropDown());
            prestationExterne.setNomMateriel("Pas de materiel");

            // update liste prestations du materiel and create entretien
            if (prestationExterne.getTypeEntretienE().equals("materiel")) {
                LocalDetails loadedLocaleDetails = localDetailsRepository.findByReferenceML(prestationExterne.getMaterielLocale().getReferenceML());
                List<PrestationExterne> prestationsM = loadedLocaleDetails.getPrestationExternes();
                prestationsM.add(prestationExterne);
                loadedLocaleDetails.setPrestationExternes(prestationsM);
                localDetailsRepository.save(loadedLocaleDetails);
                prestationExterne.setMaterielLocale(loadedLocaleDetails);
                // create entretien
                Entretien entretien = new Entretien();
                entretien.setDateEntretien(prestationExterne.getDateE());
                entretien.setLocale(loadedLocale);
                entretien.setMateriel(loadedLocaleDetails);
                entretien.setMontant(prestationExterne.getMontantFacE());
                entretien.setNumFacture(prestationExterne.getReferenceE());
                entretien.setNomLocale(loadedLocale.getDescriptionDropDown());
                entretien.setNomMateriel(loadedLocaleDetails.getDescriptionMaterielLocale());
                entretien.setPrestataire(prestationExterne.getNomPrestataireE());
                entretienRepository.save(entretien);
                // update liste entretien du locale et materiel
                // locale
                List<Entretien> localeEntretiens = loadedLocale.getEntretiens();
                localeEntretiens.add(entretien);
                loadedLocale.setEntretiens(localeEntretiens);
                localeRepository.save(loadedLocale);
                // materiel
                List<Entretien> entretiensMateriele = loadedLocaleDetails.getEntretiensMateriele();
                entretiensMateriele.add(entretien);
                loadedLocaleDetails.setEntretiensMateriele(entretiensMateriele);
                localDetailsRepository.save(loadedLocaleDetails);

                // set
                prestationExterne.setNomMateriel(loadedLocaleDetails.getDescriptionMaterielLocale());
            } else {
                prestationExterne.setMaterielLocale(null);
            }

            // set etat of reclamation to done
            if (prestationExterne.isReclamedE()) {
                Reclamation foundedReclamation = reclamationRepository.findByReference(prestationExterne.getReclamationE().getReference());
                foundedReclamation.setEtat("Bien Traitée");
                reclamationRepository.save(foundedReclamation);
                prestationExterne.setReclamationE(foundedReclamation);
            } else {
                prestationExterne.setReclamationE(null);
            }
            if (prestationExterne.isBonCommandeE()) {
                PresBonCommande bonCommande = new PresBonCommande();
                bonCommande.setReference(prestationExterne.getReferenceE());
                bonCommande.setDateBonCommande(prestationExterne.getPresBonCommandeE().getDateBonCommande());
                bonCommande.setNumeroBonCommande(prestationExterne.getPresBonCommandeE().getNumeroBonCommande());
                bonCommande.setMontantC(prestationExterne.getPresBonCommandeE().getMontantC());
                bonCommande.setNomPrestataireC(prestationExterne.getPresBonCommandeE().getNomPrestataireC());
                bonCommande.setNumeroBonCommande(prestationExterne.getPresBonCommandeE().getNumeroBonCommande());
                bonCommande.setNomPrestationAssocie(prestationExterne.getReferenceE() + ", " + prestationExterne.getTypeEntretienE());
                presBonCommandeRepository.save(bonCommande);
                prestationExterne.setPresBonCommandeE(bonCommande);
            } else {
                prestationExterne.setPresBonCommandeE(null);
            }
            if (prestationExterne.isBonLivraisonE()) {
                PresBonLivraison bonLivraison = new PresBonLivraison();
                bonLivraison.setReference(prestationExterne.getReferenceE());
                bonLivraison.setDateBonLivraison(prestationExterne.getPresBonLivraisonE().getDateBonLivraison());
                bonLivraison.setNumeroBonLivraison(prestationExterne.getPresBonLivraisonE().getNumeroBonLivraison());
                bonLivraison.setMontantL(prestationExterne.getPresBonLivraisonE().getMontantL());
                bonLivraison.setNomPrestataireL(prestationExterne.getPresBonLivraisonE().getNomPrestataireL());
                bonLivraison.setNumeroBonLivraison(prestationExterne.getPresBonLivraisonE().getNumeroBonLivraison());
                bonLivraison.setNomPrestationAssocie(prestationExterne.getReferenceE() + ", " + prestationExterne.getTypeEntretienE());
                presBonLivraisonRepository.save(bonLivraison);
                prestationExterne.setPresBonLivraisonE(bonLivraison);
            } else {
                prestationExterne.setPresBonLivraisonE(null);
            }

            // save prestation Externe
            prestationExterneRepository.save(prestationExterne);

            // set this pres to reclamation and bon commande and bon livraison si existes
            // reclamation
            if (prestationExterne.isReclamedE()) {
                Reclamation loadedReclamation = reclamationRepository.findByReference(prestationExterne.getReclamationE().getReference());
                loadedReclamation.setPrestationExterne(prestationExterne);
                reclamationRepository.save(loadedReclamation);
            }
            // bon Commande
            if (prestationExterne.isBonCommandeE()) {
                PresBonCommande loadedPresBonCommande = presBonCommandeRepository.findByReference(prestationExterne.getPresBonCommandeE().getReference());
                loadedPresBonCommande.setPrestationExterne(prestationExterne);
                presBonCommandeRepository.save(loadedPresBonCommande);
            }
            // bon Livraison
            if (prestationExterne.isBonLivraisonE()) {
                PresBonLivraison loadedPresBonLivraison = presBonLivraisonRepository.findByReference(prestationExterne.getPresBonLivraisonE().getReference());
                loadedPresBonLivraison.setPrestationExterneL(prestationExterne);
                presBonLivraisonRepository.save(loadedPresBonLivraison);
            }
            return 1;
        }
    }

    @Override
    public int update(PrestationExterne prestationExterne) {
        PrestationExterne loadedPrestationExterne = prestationExterneRepository.findByReferenceE(prestationExterne.getReferenceE());

        if (prestationExterne.getTypeEntretienE().equals("materiel") && prestationExterne.getMaterielLocale() == null) {
            return -1;
        } else if (prestationExterne.getTypeEntretienE().equals("materiel") && prestationExterne.getMaterielLocale().getReferenceML() == null) {
            return 0;
        } else if (prestationExterne.getLocale().getReference() == null) {
            return -2;
        } else if (prestationExterne.isReclamedE() && prestationExterne.getReclamationE() == null) {
            return -3;
        } else if (prestationExterne.isBonCommandeE()
                && (prestationExterne.getPresBonCommandeE().getNomPrestataireC() == null
                || prestationExterne.getPresBonCommandeE().getNumeroBonCommande() == null
                || prestationExterne.getPresBonCommandeE().getDateBonCommande() == null)) {
            return -4;
        } else if (prestationExterne.isBonLivraisonE()
                && (prestationExterne.getPresBonLivraisonE().getNomPrestataireL() == null
                || prestationExterne.getPresBonLivraisonE().getNumeroBonLivraison() == null
                || prestationExterne.getPresBonLivraisonE().getDateBonLivraison() == null)) {
            return -5;
        } else if (loadedPrestationExterne.getTypeEntretienE().equals("materiel") == false && prestationExterne.getTypeEntretienE().equals("materiel")) {
            return -6;
        } else if (loadedPrestationExterne.getTypeEntretienE().equals("materiel") && prestationExterne.getTypeEntretienE().equals("materiel") == false) {
            return -7;
        } else if (loadedPrestationExterne.isBonCommandeE() && !prestationExterne.isBonCommandeE()) {
            return -8;
        } else if (loadedPrestationExterne.isBonLivraisonE() && !prestationExterne.isBonLivraisonE()) {
            return -9;
        } else if (loadedPrestationExterne.isReclamedE() && !prestationExterne.isReclamedE()) {
            return -10;
        } else {
            loadedPrestationExterne.setTypeEntretienE(prestationExterne.getTypeEntretienE());
            loadedPrestationExterne.setDateE(prestationExterne.getDateE());
            loadedPrestationExterne.setNomPrestataireE(prestationExterne.getNomPrestataireE());
            loadedPrestationExterne.setMontantFacE(prestationExterne.getMontantFacE());
            loadedPrestationExterne.setNumeroFacE(prestationExterne.getNumeroFacE());

            // update locale 
            if (!loadedPrestationExterne.getLocale().getReference().equals(prestationExterne.getLocale().getReference())) {
                Locale loadedOldLocale = localeRepository.findByReference(loadedPrestationExterne.getLocale().getReference());
                Locale loadedNewLocale = localeRepository.findByReference(prestationExterne.getLocale().getReference());
                Entretien loadedEntretien = entretienRepository.findByNumFacture(prestationExterne.getReferenceE());
                // liste prestationsE old locale
                List<PrestationExterne> presE = loadedOldLocale.getPrestationsE();
                presE.remove(loadedPrestationExterne);
                loadedOldLocale.setPrestationsE(presE);
                // list entretiens 
                List<Entretien> entretiens = loadedOldLocale.getEntretiens();
                entretiens.remove(loadedEntretien);
                loadedOldLocale.setEntretiens(entretiens);
                localeRepository.save(loadedOldLocale);
                // liste prestationsE new locale
                List<PrestationExterne> presEN = loadedNewLocale.getPrestationsE();
                presEN.add(loadedPrestationExterne);
                loadedNewLocale.setPrestationsE(presEN);
                // list entretiens 
                List<Entretien> entretienss = loadedNewLocale.getEntretiens();
                entretienss.add(loadedEntretien);
                loadedNewLocale.setEntretiens(entretienss);
                localeRepository.save(loadedNewLocale);
                //set
                loadedPrestationExterne.setLocale(loadedNewLocale);
                loadedPrestationExterne.setNomLocale(loadedNewLocale.getDescriptionDropDown());
            }
            if (prestationExterne.getTypeEntretienE().equals("materiel") && loadedPrestationExterne.getTypeEntretienE().equals("materiel")) {
                if (!loadedPrestationExterne.getMaterielLocale().getReference().equals(prestationExterne.getMaterielLocale().getReference())) {
                    LocalDetails loadedOldMateriel = localDetailsRepository.findByReferenceML(loadedPrestationExterne.getMaterielLocale().getReferenceML());
                    LocalDetails loadedNewMateriel = localDetailsRepository.findByReferenceML(prestationExterne.getMaterielLocale().getReferenceML());
                    Entretien loadedEntretien = entretienRepository.findByNumFacture(prestationExterne.getReferenceE());
                    // liste prestationsE old materiel
                    List<PrestationExterne> presEx = loadedOldMateriel.getPrestationExternes();
                    presEx.remove(loadedPrestationExterne);
                    loadedOldMateriel.setPrestationExternes(presEx);
                    localDetailsRepository.save(loadedOldMateriel);
                    // liste prestaeionE newMateriel
                    List<PrestationExterne> presExs = loadedNewMateriel.getPrestationExternes();
                    presExs.add(loadedPrestationExterne);
                    loadedNewMateriel.setPrestationExternes(presExs);
                    localDetailsRepository.save(loadedNewMateriel);
                    // set 
                    loadedPrestationExterne.setMaterielLocale(loadedNewMateriel);
                    loadedPrestationExterne.setNomMateriel(loadedNewMateriel.getDescriptionMaterielLocale());
                    // update entretient associe
                    loadedEntretien.setMateriel(loadedNewMateriel);
                    loadedEntretien.setLocale(prestationExterne.getLocale());
                    loadedEntretien.setDateEntretien(prestationExterne.getDateE());
                    loadedEntretien.setMontant(prestationExterne.getMontantFacE());
                    loadedEntretien.setPrestataire(prestationExterne.getNomPrestataireE());
                    loadedEntretien.setNomLocale(prestationExterne.getLocale().getDescriptionDropDown());
                    loadedEntretien.setNomMateriel(loadedNewMateriel.getDescriptionMaterielLocale());
                    entretienRepository.save(loadedEntretien);
                    // update list entretiens old materiel 
                    List<Entretien> ents = loadedOldMateriel.getEntretiensMateriele();
                    ents.remove(loadedEntretien);
                    loadedOldMateriel.setEntretiensMateriele(ents);
                    localDetailsRepository.save(loadedOldMateriel);
                    // update list entretiens new matereil
                    List<Entretien> entss = loadedNewMateriel.getEntretiensMateriele();
                    entss.remove(loadedEntretien);
                    loadedNewMateriel.setEntretiensMateriele(entss);
                    localDetailsRepository.save(loadedNewMateriel);
                }
            }

            if (loadedPrestationExterne.isBonCommandeE() && prestationExterne.isBonCommandeE()) {
                PresBonCommande loadedBonCommande = presBonCommandeRepository.findByReference(loadedPrestationExterne.getReferenceE());
                loadedBonCommande.setReference(prestationExterne.getReferenceE());
                loadedBonCommande.setDateBonCommande(prestationExterne.getPresBonCommandeE().getDateBonCommande());
                loadedBonCommande.setNumeroBonCommande(prestationExterne.getPresBonCommandeE().getNumeroBonCommande());
                loadedBonCommande.setMontantC(prestationExterne.getPresBonCommandeE().getMontantC());
                loadedBonCommande.setNomPrestataireC(prestationExterne.getPresBonCommandeE().getNomPrestataireC());
                loadedBonCommande.setNumeroBonCommande(prestationExterne.getPresBonCommandeE().getNumeroBonCommande());
                loadedBonCommande.setNomPrestationAssocie(prestationExterne.getReferenceE() + ", " + prestationExterne.getTypeEntretienE());
                loadedBonCommande.setPrestationExterne(loadedPrestationExterne);
                presBonCommandeRepository.save(loadedBonCommande);
            } else if (!loadedPrestationExterne.isBonCommandeE() && prestationExterne.isBonCommandeE()) {
                PresBonCommande bonCommande = new PresBonCommande();
                bonCommande.setReference(prestationExterne.getReferenceE());
                bonCommande.setDateBonCommande(prestationExterne.getPresBonCommandeE().getDateBonCommande());
                bonCommande.setNumeroBonCommande(prestationExterne.getPresBonCommandeE().getNumeroBonCommande());
                bonCommande.setMontantC(prestationExterne.getPresBonCommandeE().getMontantC());
                bonCommande.setNomPrestataireC(prestationExterne.getPresBonCommandeE().getNomPrestataireC());
                bonCommande.setNumeroBonCommande(prestationExterne.getPresBonCommandeE().getNumeroBonCommande());
                bonCommande.setNomPrestationAssocie(prestationExterne.getReferenceE() + ", " + prestationExterne.getTypeEntretienE());
                bonCommande.setPrestationExterne(loadedPrestationExterne);
                presBonCommandeRepository.save(bonCommande);
                loadedPrestationExterne.setPresBonCommandeE(bonCommande);
                loadedPrestationExterne.setBonCommandeE(true);
            }
            if (loadedPrestationExterne.isBonLivraisonE() && prestationExterne.isBonLivraisonE()) {
                PresBonLivraison loadedBonLivraison = presBonLivraisonRepository.findByReference(loadedPrestationExterne.getReferenceE());
                loadedBonLivraison.setReference(prestationExterne.getReferenceE());
                loadedBonLivraison.setDateBonLivraison(prestationExterne.getPresBonLivraisonE().getDateBonLivraison());
                loadedBonLivraison.setNumeroBonLivraison(prestationExterne.getPresBonLivraisonE().getNumeroBonLivraison());
                loadedBonLivraison.setMontantL(prestationExterne.getPresBonLivraisonE().getMontantL());
                loadedBonLivraison.setNomPrestataireL(prestationExterne.getPresBonLivraisonE().getNomPrestataireL());
                loadedBonLivraison.setNumeroBonLivraison(prestationExterne.getPresBonLivraisonE().getNumeroBonLivraison());
                loadedBonLivraison.setNomPrestationAssocie(prestationExterne.getReferenceE() + ", " + prestationExterne.getTypeEntretienE());
                loadedBonLivraison.setPrestationExterneL(loadedPrestationExterne);
                presBonLivraisonRepository.save(loadedBonLivraison);
            } else if (!loadedPrestationExterne.isBonLivraisonE() && prestationExterne.isBonLivraisonE()) {
                PresBonLivraison bonLivraison = new PresBonLivraison();
                bonLivraison.setReference(prestationExterne.getReferenceE());
                bonLivraison.setDateBonLivraison(prestationExterne.getPresBonLivraisonE().getDateBonLivraison());
                bonLivraison.setNumeroBonLivraison(prestationExterne.getPresBonLivraisonE().getNumeroBonLivraison());
                bonLivraison.setMontantL(prestationExterne.getPresBonLivraisonE().getMontantL());
                bonLivraison.setNomPrestataireL(prestationExterne.getPresBonLivraisonE().getNomPrestataireL());
                bonLivraison.setNumeroBonLivraison(prestationExterne.getPresBonLivraisonE().getNumeroBonLivraison());
                bonLivraison.setNomPrestationAssocie(prestationExterne.getReferenceE() + ", " + prestationExterne.getTypeEntretienE());
                bonLivraison.setPrestationExterneL(loadedPrestationExterne);
                presBonLivraisonRepository.save(bonLivraison);
                loadedPrestationExterne.setPresBonLivraisonE(bonLivraison);
                loadedPrestationExterne.setBonLivraisonE(true);
            }
            if (loadedPrestationExterne.isReclamedE() && prestationExterne.isReclamedE()) {
                if (!loadedPrestationExterne.getReclamationE().getReference().equals(prestationExterne.getReclamationE().getReference())) {
                    Reclamation oldReclamation = reclamationRepository.findByReference(loadedPrestationExterne.getReclamationE().getReference());
                    Reclamation newReclamation = reclamationRepository.findByReference(prestationExterne.getReclamationE().getReference());
                    oldReclamation.setEtat("Sous Traitement");
                    oldReclamation.setPrestationExterne(null);
                    reclamationRepository.save(oldReclamation);
                    newReclamation.setEtat("Bien Traitée");
                    newReclamation.setPrestationExterne(loadedPrestationExterne);
                    reclamationRepository.save(newReclamation);
                    loadedPrestationExterne.setReclamationE(newReclamation);
                }
            } else if (!loadedPrestationExterne.isReclamedE() && prestationExterne.isReclamedE()) {
                Reclamation foundedReclamation = reclamationRepository.findByReference(prestationExterne.getReclamationE().getReference());
                foundedReclamation.setEtat("Bien Traitée");
                foundedReclamation.setPrestationExterne(loadedPrestationExterne);
                reclamationRepository.save(foundedReclamation);
                loadedPrestationExterne.setReclamedE(true);
                loadedPrestationExterne.setReclamationE(foundedReclamation);
            }
            prestationExterneRepository.save(loadedPrestationExterne);
            return 1;
        }
    }

    @Override
    public int delete(String reference) {
        PrestationExterne foundedPrestationExterne = prestationExterneRepository.findByReferenceE(reference);
        // set prestExterne to null in the reclamation 
        if (foundedPrestationExterne.getReclamationE() != null) {
            Reclamation LoadedReclamation = reclamationRepository.findByReference(foundedPrestationExterne.getReclamationE().getReference());
            LoadedReclamation.setPrestationExterne(null);
            LoadedReclamation.setEtat("Sous Traitement");
            reclamationRepository.save(LoadedReclamation);
        }
        if (foundedPrestationExterne.getPresBonCommandeE() != null) {
            PresBonCommande foundedPresBonCommande = presBonCommandeRepository.findByReference(foundedPrestationExterne.getPresBonCommandeE().getReference());
            presBonCommandeRepository.delete(foundedPresBonCommande);
        }
        if (foundedPrestationExterne.getPresBonLivraisonE() != null) {
            PresBonLivraison foundedPresBonLivraison = presBonLivraisonRepository.findByReference(foundedPrestationExterne.getPresBonLivraisonE().getReference());
            presBonLivraisonRepository.delete(foundedPresBonLivraison);
        }

        prestationExterneRepository.delete(foundedPrestationExterne);
        return 1;
    }

    @Override
    public List<PrestationExterne> findAll() {
        return prestationExterneRepository.findAll();
    }

}
