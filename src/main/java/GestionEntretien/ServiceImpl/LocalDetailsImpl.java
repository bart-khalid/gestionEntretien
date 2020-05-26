/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.Entretien;
import GestionEntretien.Bean.LocalDetails;
import GestionEntretien.Bean.Locale;
import GestionEntretien.Bean.Materiel;
import GestionEntretien.Bean.PrestationExterne;
import GestionEntretien.Bean.PrestationInterne;
import GestionEntretien.Bean.Reclamation;
import GestionEntretien.Dao.EntretienRepository;
import GestionEntretien.Dao.LocalDetailsRepository;
import GestionEntretien.Dao.LocaleRepository;
import GestionEntretien.Dao.MaterielRepository;
import GestionEntretien.Dao.PrestationExterneRepository;
import GestionEntretien.Dao.PrestationInterneRepository;
import GestionEntretien.Dao.ReclamationRepository;
import GestionEntretien.Service.LocalDetailsService;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class LocalDetailsImpl implements LocalDetailsService {

    @Autowired
    private LocalDetailsRepository localDetailsRepository;

    @Autowired
    private MaterielRepository materielRepository;

    @Autowired
    private LocaleRepository localeRepository;
     @Autowired
    private EntretienRepository entretienRepository;
    @Autowired
    private PrestationInterneRepository prestationInterneRepository;
    @Autowired
    private PrestationExterneRepository prestationExterneRepository;
    @Autowired
    private ReclamationRepository reclamationRepository;
    

    @Override
    public int save(LocalDetails localDetails) {
        LocalDetails foundedMaterielLocal = localDetailsRepository.findByReferenceML(localDetails.getReferenceML());
        if (foundedMaterielLocal != null) {
            return -1;
        } else if(localDetails.getLocale() == null || localDetails.getLocale().getReference() == null){
            return -2;
        } else {

            //update locale associe a ce materielLocale
            Locale loadedLocale = localeRepository.findByReference(localDetails.getLocale().getReference());
            List<LocalDetails> materielsLocle = loadedLocale.getLocalDetails();
            materielsLocle.add(localDetails);
            loadedLocale.setLocalDetails(materielsLocle);
            loadedLocale.setNbrMateriel(loadedLocale.getNbrMateriel() + 1);
            localeRepository.save(loadedLocale);

            //update materiel associe a ce materielLocale
            Materiel loadedMateriel = materielRepository.findByReference(localDetails.getMateriel().getReference());
            List<LocalDetails> materiels = loadedMateriel.getLocalDetails();
            materiels.add(localDetails);
            loadedMateriel.setLocalDetails(materiels);
            loadedMateriel.setNbrEntite(loadedMateriel.getNbrEntite() + 1);
            materielRepository.save(loadedMateriel);

            //save du Nom LOcale et Nom materiel
            localDetails.setLocaleAssocie(localDetails.getLocale().getDescriptionDropDown());
            localDetails.setMaterielLocale(localDetails.getMateriel().getDescriptionDropDown());
            localDetails.setDescriptionMaterielLocale(localDetails.getReferenceML() + ", " + localDetails.getMaterielLocale());
            LocalDetails.setNbr(LocalDetails.getNbr() + 1);
            localDetails.setReference(RandomStringUtils.random(3, true, false) + String.valueOf(LocalDetails.getNbr()));
            while (foundedMaterielLocal != null) {
            localDetails.setReference(RandomStringUtils.random(3, true, false) + String.valueOf(LocalDetails.getNbr()));
            foundedMaterielLocal = localDetailsRepository.findByReference(localDetails.getReference());
        }
            localDetails.setReferenceML(localDetails.getReferenceML());
            localDetailsRepository.save(localDetails);
            return 1;
        }
    }

    @Override
    public int update(LocalDetails localDetails) {
        LocalDetails foundedMaterielLocale = localDetailsRepository.findByReference(localDetails.getReference());
        LocalDetails foundedMaterielLoc = localDetailsRepository.findByReferenceML(localDetails.getReferenceML());
        
        if(localDetails.getLocale() == null || localDetails.getLocale().getReference() == null){
        return -1;
        }
        else if(foundedMaterielLoc != null && !(foundedMaterielLocale.getReferenceML().equals(localDetails.getReferenceML()))){
        return -2;
        }
        else{
            // update du locale associe 
            if (!foundedMaterielLocale.getLocale().getReference().equals(localDetails.getLocale().getReference())) {

                //modifier lancien locale associe 
                List<LocalDetails> materielslocaleToEdit = foundedMaterielLocale.getLocale().getLocalDetails();
                materielslocaleToEdit.remove(foundedMaterielLocale);
                foundedMaterielLocale.getLocale().setLocalDetails(materielslocaleToEdit);
                foundedMaterielLocale.getLocale().setNbrMateriel(foundedMaterielLocale.getLocale().getNbrMateriel() - 1);
                localeRepository.save(foundedMaterielLocale.getLocale());

                Locale loadedLocale = localeRepository.findByReference(localDetails.getLocale().getReference());
                if (loadedLocale != null) {
                    List<LocalDetails> materielsLocle = loadedLocale.getLocalDetails();
                    materielsLocle.add(localDetails);
                    loadedLocale.setLocalDetails(materielsLocle);
                    loadedLocale.setNbrMateriel(loadedLocale.getNbrMateriel() + 1);
                    localeRepository.save(loadedLocale);
                }
                //update new Locale asoocie    
                foundedMaterielLocale.setLocale(loadedLocale);
            }

            //update du Materiel Associer
            if (!foundedMaterielLocale.getMateriel().getReference().equals(localDetails.getMateriel().getReference())) {

                //modifier l' ancien Materiel associer
                List<LocalDetails> mats = foundedMaterielLocale.getMateriel().getLocalDetails();
                mats.remove(foundedMaterielLocale);
                foundedMaterielLocale.getMateriel().setLocalDetails(mats);
                foundedMaterielLocale.getMateriel().setNbrEntite(foundedMaterielLocale.getMateriel().getNbrEntite() - 1);
                materielRepository.save(foundedMaterielLocale.getMateriel());

                Materiel loadedMateriel = materielRepository.findByReference(localDetails.getMateriel().getReference());
                //update new Matriel associe
                foundedMaterielLocale.setMateriel(loadedMateriel);
                // list
                List<LocalDetails> materiels = loadedMateriel.getLocalDetails();
                materiels.add(localDetails);
                loadedMateriel.setLocalDetails(materiels);
                loadedMateriel.setNbrEntite(loadedMateriel.getNbrEntite() + 1);
                materielRepository.save(loadedMateriel);
            }

            //update attributes of this MaterielLocale
            foundedMaterielLocale.setLocaleAssocie(localDetails.getLocale().getDescriptionDropDown());
            foundedMaterielLocale.setMaterielLocale(localDetails.getMateriel().getDescriptionDropDown());
            foundedMaterielLocale.setDateAffectation(localDetails.getDateAffectation());
            localDetails.setDescriptionMaterielLocale(localDetails.getReferenceML() + ", " + localDetails.getMaterielLocale());
            foundedMaterielLocale.setReferenceML(localDetails.getReferenceML());
            
            localDetailsRepository.save(foundedMaterielLocale);

            return 1;
        }
        
    }

    @Override
    public int delete(String referenceMaterielLocal) {
        LocalDetails foundedMaterielLocale = localDetailsRepository.findByReference(referenceMaterielLocal);
        //Edit locale associer
        if (foundedMaterielLocale.getLocale() != null) {
            Locale loadedLocale = localeRepository.findByReference(foundedMaterielLocale.getLocale().getReference());
            List<LocalDetails> materielslocaleToEdit = loadedLocale.getLocalDetails();
            materielslocaleToEdit.remove(foundedMaterielLocale);
            loadedLocale.setLocalDetails(materielslocaleToEdit);
            loadedLocale.setNbrMateriel(loadedLocale.getNbrMateriel() - 1);
            localeRepository.save(loadedLocale);
        }

        //Edit materiel asoocier
        if (foundedMaterielLocale.getMateriel() != null) {
            Materiel loadedMateriel = materielRepository.findByReference(foundedMaterielLocale.getMateriel().getReference());
            List<LocalDetails> mats = loadedMateriel.getLocalDetails();
            mats.remove(foundedMaterielLocale);
            loadedMateriel.setLocalDetails(mats);
            loadedMateriel.setNbrEntite(foundedMaterielLocale.getMateriel().getNbrEntite() - 1);
            materielRepository.save(loadedMateriel);
        }

        // list of prestations associate
        List<PrestationInterne> preIs = foundedMaterielLocale.getPrestationInternes();
        List<PrestationExterne> preEs = foundedMaterielLocale.getPrestationExternes();
        // set materiel to null
        preIs.forEach((pre) -> {
            pre.setMaterielLocale(null);
            prestationInterneRepository.save(pre);
        });
        preEs.forEach((pre) -> {
            pre.setMaterielLocale(null);
            prestationExterneRepository.save(pre);
        });

        // entretiens 
        List<Entretien> ents = foundedMaterielLocale.getEntretiensMateriele();
        ents.forEach((ent) -> {
            ent.setMateriel(null);
            entretienRepository.save(ent);
        });

        // reclamations   
        List<Reclamation> recs = foundedMaterielLocale.getReclamations();
        recs.forEach((rec) -> {
            rec.setMateriel(null);
            reclamationRepository.save(rec);
        });

        //delete this localeMateriels
        localDetailsRepository.delete(foundedMaterielLocale);
        return 1;
    }

    @Override
    public List<LocalDetails> findAll() {
        return localDetailsRepository.findAll();
    }
}
