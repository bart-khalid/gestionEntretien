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

        //generate random string
        PrestationInterne.setNbrPresInterne(PrestationInterne.getNbrPresInterne() + 1);
        preInterne.setReferenceI(RandomStringUtils.random(6, true, false) + String.valueOf(PrestationInterne.getNbrPresInterne()));
        PrestationInterne foundedPrestataionInterne = prestationInterneRepository.findByReferenceI(preInterne.getReferenceI());
        while (foundedPrestataionInterne != null) {
            preInterne.setReferenceI(RandomStringUtils.random(6, true, false) + String.valueOf(PrestationInterne.getNbrPresInterne()));
            foundedPrestataionInterne = prestationInterneRepository.findByReferenceI(preInterne.getReferenceI());
        }

        preInterne.setNomAgentI(preInterne.getAgent().getCodeAgent() + ", " + preInterne.getAgent().getNomAgent());
        preInterne.setNomMaterielI("Pas de materiel");

        // update liste prestationInterne du locale
        Locale foundedLocale = localeRepository.findByReference(preInterne.getLocale().getReference());
        List<PrestationInterne> presInts = foundedLocale.getPrestationsI();
        presInts.add(preInterne);
        foundedLocale.setPrestationsI(presInts);
        localeRepository.save(foundedLocale);

        preInterne.setNomLocaleI(foundedLocale.getDescriptionDropDown());

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

            // creation de fiche entretien
            Entretien ent = new Entretien(preInterne.getDateI(), foundedMateriel.getMaterielLocale(), preInterne.getNomAgentI(), 0, preInterne.getReferenceI());
            ent.setLoclale(foundedLocale);
            ent.setMateriel(foundedMateriel);
            ent.setNomLocale(foundedLocale.getDescriptionDropDown());
            entretienRepository.save(ent);

            // update liste des entretiens
            List<Entretien> entretiens = foundedMateriel.getEntretiensMateriele();
            entretiens.add(ent);
            foundedMateriel.setEntretiensMateriele(entretiens);
            localDetailsRepository.save(foundedMateriel);

            preInterne.setNomMaterielI(foundedMateriel.getDescriptionMaterielLocale());
        } else {
            preInterne.setMaterielLocale(null);
        }

        prestationInterneRepository.save(preInterne);
        return 1;
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
            if (!foundedPrestationInterne.getReclamationI().getReference().equals(preInterne.getReclamationI().getReference())) {
                Reclamation foundedReclamationold = reclamationRepository.findByReference(foundedPrestationInterne.getRefrenceReclamationI());
                Reclamation foundedReclamationNew = reclamationRepository.findByReference(preInterne.getRefrenceReclamationI());

                foundedReclamationold.setEtat("Sous Traitement");
                foundedReclamationNew.setEtat("Bien Traitée");
                reclamationRepository.save(foundedReclamationNew);
                reclamationRepository.save(foundedReclamationold);
            }
        } else if (!preInterne.isReclamedI() && foundedPrestationInterne.isReclamedI()) {
            Reclamation loadedReclamation = reclamationRepository.findByReference(foundedPrestationInterne.getReclamationI().getReference());
            loadedReclamation.setEtat("Sous Traitement");
            reclamationRepository.save(loadedReclamation);
            foundedPrestationInterne.setReclamationI(null);
        } else {
            foundedPrestationInterne.setReclamationI(null);
        }

        //si on change l'agent
        if (!foundedPrestationInterne.getAgent().getCodeAgent().equals(preInterne.getAgent().getCodeAgent())) {
            foundedPrestationInterne.setAgent(preInterne.getAgent());
            foundedPrestationInterne.setNomAgentI(preInterne.getAgent().getNomAgent() + ", " + preInterne.getAgent().getCodeAgent());
        }

        // modifier lister presInterne old locale
        Locale foundedLocaleOld = localeRepository.findByReference(foundedPrestationInterne.getLocale().getReference());
        List<PrestationInterne> presIntsOld = foundedLocaleOld.getPrestationsI();
        presIntsOld.remove(preInterne);
        foundedLocaleOld.setPrestationsI(presIntsOld);
        localeRepository.save(foundedLocaleOld);

        // update liste prestationInterne du nv locale
        Locale foundedLocale = localeRepository.findByReference(preInterne.getLocale().getReference());
        List<PrestationInterne> presInts = foundedLocale.getPrestationsI();
        presInts.add(preInterne);
        foundedLocale.setPrestationsI(presInts);
        localeRepository.save(foundedLocale);

        preInterne.setNomLocaleI(foundedLocale.getDescriptionDropDown());
        // fin update nv locale

        //update Materiel
        if (preInterne.getTypeEntretienI().equals("materiel")) {
            // find Entretien associe
            Entretien loadedEntretien = entretienRepository.findByNumFacture(foundedPrestationInterne.getReferenceI());

            // old materiel
            LocalDetails foundedMaterielOld = localDetailsRepository.findByReferenceML(preInterne.getMaterielLocale().getReferenceML());
            List<Entretien> entretiens = foundedMaterielOld.getEntretiensMateriele();
            entretiens.remove(loadedEntretien);
            foundedMaterielOld.setEntretiensMateriele(entretiens);
            localDetailsRepository.save(foundedMaterielOld);

            // new Materiel
            LocalDetails foundedMaterielNew = localDetailsRepository.findByReferenceML(preInterne.getMaterielLocale().getReferenceML());
            foundedPrestationInterne.setMaterielLocale(foundedMaterielNew);
            loadedEntretien.setLoclale(foundedLocale);
            loadedEntretien.setMateriel(foundedMaterielNew);
            loadedEntretien.setNomLocale(foundedLocale.getDescriptionDropDown());
            entretienRepository.save(loadedEntretien);

            //update entretiens new materiel
            List<Entretien> entretienss = foundedMaterielNew.getEntretiensMateriele();
            entretiens.add(loadedEntretien);
            foundedMaterielNew.setEntretiensMateriele(entretiens);
            localDetailsRepository.save(foundedMaterielNew);
            // update name materiel associe
            preInterne.setNomMaterielI(foundedMaterielNew.getDescriptionMaterielLocale());

        }

        prestationInterneRepository.save(preInterne);
        return 1;
    }

    @Override
    public int delete(String reference) {
        PrestationInterne foundedPreInterne = prestationInterneRepository.findByReferenceI(reference);
        // update liste pres du locale
        Locale loadedLOcale = localeRepository.findByReference(foundedPreInterne.getLocale().getReference());
        List<PrestationInterne> press = loadedLOcale.getPrestationsI();
        press.remove(foundedPreInterne);
        loadedLOcale.setPrestationsI(press);
        localeRepository.save(loadedLOcale);

        // update reclamation associe
        if (foundedPreInterne.isReclamedI()) {
            Reclamation loadedReclamation = reclamationRepository.findByReference(foundedPreInterne.getReclamationI().getReference());
            loadedReclamation.setEtat("Sous Traitement");
            reclamationRepository.save(loadedReclamation);
        }

        prestationInterneRepository.delete(foundedPreInterne);
        return 1;
    }

    @Override
    public List<PrestationInterne> findAll() {
        return prestationInterneRepository.findAll();
    }

}
