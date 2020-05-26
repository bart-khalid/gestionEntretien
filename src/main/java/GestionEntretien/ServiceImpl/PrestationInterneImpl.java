/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.Entretien;
import GestionEntretien.Bean.LocalDetails;
import GestionEntretien.Bean.Locale;
import GestionEntretien.Bean.PrestationInterne;
import GestionEntretien.Bean.Reclamation;
import GestionEntretien.Dao.EntretienRepository;
import GestionEntretien.Dao.LocalDetailsRepository;
import GestionEntretien.Dao.LocaleRepository;
import GestionEntretien.Dao.PrestationInterneRepository;
import GestionEntretien.Dao.ReclamationRepository;
import GestionEntretien.Service.PrestationInterneService;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class PrestationInterneImpl implements PrestationInterneService {

    @Autowired
    private PrestationInterneRepository prestationInterneRepository;

    @Autowired
    private ReclamationRepository reclamationRepository;

    @Autowired
    private EntretienRepository entretienRepository;

    @Autowired
    private LocalDetailsRepository localDetailsRepository;

    @Autowired
    private LocaleRepository localeRepository;

    @Override
    public int save(PrestationInterne preInterne) {

        if (preInterne.getAgent().getNomAgent() == null || preInterne.getAgent().getNomAgent().equals("") || preInterne.getLocale().getNomLocal() == null || preInterne.getLocale().getNomLocal().equals("")) {
            return -2;
        }else if(preInterne.getTypeEntretienI().equals("materiel") && preInterne.getMaterielLocale().getReferenceML() == null){
            return -3;
        }else {
            //generate random string
        PrestationInterne.setNbrPresInterne(PrestationInterne.getNbrPresInterne() + 1);
        preInterne.setReferenceI(RandomStringUtils.random(6, true, false) + String.valueOf(PrestationInterne.getNbrPresInterne()));
        PrestationInterne foundedPrestataionInterne = prestationInterneRepository.findByReferenceI(preInterne.getReferenceI());
        while (foundedPrestataionInterne != null) {
            preInterne.setReferenceI(RandomStringUtils.random(6, true, false) + String.valueOf(PrestationInterne.getNbrPresInterne()));
            foundedPrestataionInterne = prestationInterneRepository.findByReferenceI(preInterne.getReferenceI());
        }
        // fin generate random string

        
        // set
        preInterne.setNomAgentI(preInterne.getAgent().getCodeAgent() + ", " + preInterne.getAgent().getNomAgent());
        preInterne.setNomMaterielI("Pas de materiel");

        // update liste prestationInterne du locale
        addPrestationToListOfPrestationsofLocale(preInterne);

        // set attribute NomLocale
        preInterne.setNomLocaleI(preInterne.getLocale().getDescriptionDropDown());

        //si reclamer alors regler letat de la reclamation associe
        if (preInterne.isReclamedI()) {
            Reclamation foundedRclamation = reclamationRepository.findByReference(preInterne.getReclamationI().getReference());
            foundedRclamation.setEtat("Bien Traitée");
            reclamationRepository.save(foundedRclamation);
        } else {
            preInterne.setReclamationI(null);
        }

        //si pres d un materiel donc il sagit d un entretien
        if (preInterne.getTypeEntretienI().equals("materiel")) {

            // find from database
            LocalDetails foundedMateriel = localDetailsRepository.findByReferenceML(preInterne.getMaterielLocale().getReferenceML());

            // update liste prestationsInterne of materiel
            addPrestationToListOfPrestationsOfMateriel(foundedMateriel, preInterne);

            // creation de fiche entretien
            Entretien ent = new Entretien(preInterne.getDateI(), foundedMateriel.getDescriptionMaterielLocale(), preInterne.getNomAgentI(), 0, preInterne.getReferenceI());
            ent.setLocale(preInterne.getLocale());
            ent.setMateriel(foundedMateriel);
            ent.setNomLocale(preInterne.getLocale().getDescriptionDropDown());
            entretienRepository.save(ent);

            // update liste des entretiens
            addEntretienToListOfEntretiensOfMateriel(foundedMateriel, ent);

            // update list entretien of locale
            addEntretienToListOfEntretiensOfLocale(preInterne.getLocale(), ent);

            // set nom Materiel
            preInterne.setNomMaterielI(foundedMateriel.getDescriptionMaterielLocale());
        } else {
            preInterne.setMaterielLocale(null);
        }

        prestationInterneRepository.save(preInterne);
        // set this prestation to reclamation
        if (preInterne.isReclamedI()) {
            Reclamation loadedReclamation = reclamationRepository.findByReference(preInterne.getReclamationI().getReference());
            loadedReclamation.setPrestationInterne(preInterne);
            reclamationRepository.save(loadedReclamation);
        }
        return 1;
        }
    }

    @Override
    public int update(PrestationInterne preInterne) {
        PrestationInterne foundedPrestationInterne = prestationInterneRepository.findByReferenceI(preInterne.getReferenceI());

        //update attriibutes de bases
        foundedPrestationInterne.setTypeEntretienI(preInterne.getTypeEntretienI());
        foundedPrestationInterne.setDateI(preInterne.getDateI());
        foundedPrestationInterne.setLocale(preInterne.getLocale());

        //update etat reclamation associer
        if (foundedPrestationInterne.isReclamedI() && preInterne.isReclamedI()) {
            if (foundedPrestationInterne.getReclamationI() == null && preInterne.getReclamationI() == null) {
                return -2;
            } else if (!foundedPrestationInterne.getReclamationI().getReference().equals(preInterne.getReclamationI().getReference())) {
                Reclamation foundedReclamationold = reclamationRepository.findByReference(foundedPrestationInterne.getRefrenceReclamationI());
                Reclamation foundedReclamationNew = reclamationRepository.findByReference(preInterne.getRefrenceReclamationI());

                foundedReclamationold.setEtat("Sous Traitement");
                foundedReclamationNew.setEtat("Bien Traitée");
                reclamationRepository.save(foundedReclamationNew);
                reclamationRepository.save(foundedReclamationold);
            }
        } else {
            foundedPrestationInterne.setReclamationI(null);
        }

        //si on change l'agent
        if (!foundedPrestationInterne.getAgent().getCodeAgent().equals(preInterne.getAgent().getCodeAgent())) {
            foundedPrestationInterne.setAgent(preInterne.getAgent());
            foundedPrestationInterne.setNomAgentI(preInterne.getAgent().getNomAgent() + ", " + preInterne.getAgent().getCodeAgent());
        }

        // modifier liste presInterne old locale
        removePrestationToListOfPrestationsofLocale(foundedPrestationInterne);

        // update liste prestationInterne new locale
        addPrestationToListOfPrestationsofLocale(preInterne);

        // set NomLocale
        preInterne.setNomLocaleI(preInterne.getLocale().getDescriptionDropDown());

        //update Materiel
        if (preInterne.getTypeEntretienI().equals("materiel")) {
            if (preInterne.getMaterielLocale() == null) {
                return -2;
            } else {
                // find Entretien associe
                Entretien loadedEntretien = entretienRepository.findByNumFacture(foundedPrestationInterne.getReferenceI());

                // old materiel
                if (foundedPrestationInterne.getMaterielLocale() != null) {
                    LocalDetails foundedMaterielOld = localDetailsRepository.findByReferenceML(foundedPrestationInterne.getMaterielLocale().getReferenceML());
                    removeEntretienToListOfEntretiensOfMateriel(foundedMaterielOld, loadedEntretien);
                    removePrestationFromListOfPrestationsOfMateriel(foundedMaterielOld, foundedPrestationInterne);
                }

                // new 
                LocalDetails foundedMaterielNew = localDetailsRepository.findByReferenceML(preInterne.getMaterielLocale().getReferenceML());
                if (loadedEntretien != null) {
                    foundedPrestationInterne.setMaterielLocale(foundedMaterielNew);
                    loadedEntretien.setLocale(preInterne.getLocale());
                    loadedEntretien.setMateriel(foundedMaterielNew);
                    loadedEntretien.setNomLocale(preInterne.getLocale().getDescriptionDropDown());
                    entretienRepository.save(loadedEntretien);
                } else {
                    // creation de fiche entretien
                    Entretien ent = new Entretien(preInterne.getDateI(), foundedMaterielNew.getMaterielLocale(), preInterne.getNomAgentI(), 0, preInterne.getReferenceI());
                    ent.setLocale(preInterne.getLocale());
                    ent.setMateriel(foundedMaterielNew);
                    ent.setNomLocale(preInterne.getLocale().getDescriptionDropDown());
                    entretienRepository.save(ent);
                }

                // new materiel
                addEntretienToListOfEntretiensOfMateriel(foundedMaterielNew, loadedEntretien);
                addPrestationToListOfPrestationsOfMateriel(foundedMaterielNew, foundedPrestationInterne);

                // update name materiel associe
                preInterne.setNomMaterielI(foundedMaterielNew.getDescriptionMaterielLocale());

            }
        } else {
            preInterne.setNomMaterielI("Pas de materiel");
        }

        prestationInterneRepository.save(preInterne);
        // set this prestation to reclamation
        if (preInterne.isReclamedI()) {
            Reclamation loadedReclamation = reclamationRepository.findByReference(preInterne.getReclamationI().getReference());
            loadedReclamation.setPrestationInterne(preInterne);
            reclamationRepository.save(loadedReclamation);
        }
        return 1;
    }

    @Override
    public int delete(String reference) {
        PrestationInterne foundedPreInterne = prestationInterneRepository.findByReferenceI(reference);
        // update liste pres du locale
        if (foundedPreInterne.getLocale() != null) {
            removePrestationToListOfPrestationsofLocale(foundedPreInterne);
        }

        // update liste pres materiel if materiel not null
        if (foundedPreInterne.getMaterielLocale() != null) {
            LocalDetails materiel = localDetailsRepository.findByReferenceML(foundedPreInterne.getMaterielLocale().getReferenceML());
            removePrestationFromListOfPrestationsOfMateriel(materiel, foundedPreInterne);
        }

        // update reclamation associe
        if (foundedPreInterne.isReclamedI()) {
            if(foundedPreInterne.getReclamationI() != null){
            Reclamation loadedReclamation = reclamationRepository.findByReference(foundedPreInterne.getReclamationI().getReference());
            loadedReclamation.setEtat("Sous Traitement");
            loadedReclamation.setPrestationInterne(null);
            reclamationRepository.save(loadedReclamation);
        }
        }

        prestationInterneRepository.delete(foundedPreInterne);
        return 1;
    }

    @Override
    public List<PrestationInterne> findAll() {
        return prestationInterneRepository.findAll();
    }

    // functions
    // locale && prestation
    public void addPrestationToListOfPrestationsofLocale(PrestationInterne pre) {
        Locale foundedLocale = localeRepository.findByReference(pre.getLocale().getReference());
        List<PrestationInterne> presInts = foundedLocale.getPrestationsI();
        presInts.add(pre);
        foundedLocale.setPrestationsI(presInts);
        localeRepository.save(foundedLocale);
    }

    public void removePrestationToListOfPrestationsofLocale(PrestationInterne pre) {
        Locale foundedLocale = localeRepository.findByReference(pre.getLocale().getReference());
        List<PrestationInterne> presInts = foundedLocale.getPrestationsI();
        presInts.remove(pre);
        foundedLocale.setPrestationsI(presInts);
        localeRepository.save(foundedLocale);
    }

    // materil && prestation
    public void addPrestationToListOfPrestationsOfMateriel(LocalDetails materiel, PrestationInterne prestation) {
        List<PrestationInterne> prestations = materiel.getPrestationInternes();
        prestations.add(prestation);
        materiel.setPrestationInternes(prestations);
        localDetailsRepository.save(materiel);
    }

    public void removePrestationFromListOfPrestationsOfMateriel(LocalDetails materiel, PrestationInterne prestation) {
        List<PrestationInterne> prestations = materiel.getPrestationInternes();
        prestations.remove(prestation);
        materiel.setPrestationInternes(prestations);
        localDetailsRepository.save(materiel);
    }

    // mateiel && entretiens 
    public void addEntretienToListOfEntretiensOfMateriel(LocalDetails materiel, Entretien ent) {
        List<Entretien> ents = materiel.getEntretiensMateriele();
        ents.add(ent);
        materiel.setEntretiensMateriele(ents);
        localDetailsRepository.save(materiel);
    }

    public void removeEntretienToListOfEntretiensOfMateriel(LocalDetails materiel, Entretien ent) {
        List<Entretien> ents = materiel.getEntretiensMateriele();
        ents.remove(ent);
        materiel.setEntretiensMateriele(ents);
        localDetailsRepository.save(materiel);
    }

    // locale && entretiens
    public void addEntretienToListOfEntretiensOfLocale(Locale locale, Entretien ent) {
        Locale foundedLocale = localeRepository.findByReference(locale.getReference());
        List<Entretien> ents = foundedLocale.getEntretiens();
        ents.add(ent);
        foundedLocale.setEntretiens(ents);
        localeRepository.save(foundedLocale);
    }

    public void removeEntretienToListOfEntretiensOfLocale(Locale locale, Entretien ent) {
        Locale foundedLocale = localeRepository.findByReference(locale.getReference());
        List<Entretien> ents = foundedLocale.getEntretiens();
        ents.remove(ent);
        foundedLocale.setEntretiens(ents);
        localeRepository.save(foundedLocale);
    }

}
