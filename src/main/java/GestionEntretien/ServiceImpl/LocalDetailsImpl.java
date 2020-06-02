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
    private MaterielRepository foundedMaterielrielRepository;

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

    @Autowired
    private LocaleImpl localimpl;

    @Override
    public int save(LocalDetails localDetails) {
        LocalDetails foundedMaterielLocal = localDetailsRepository.findByReferenceML(localDetails.getReferenceML());
        if (foundedMaterielLocal != null) {
            return -1;
        } else if (localDetails.getLocale() == null || localDetails.getLocale().getReference() == null) {
            return -2;
        } else {

            //update locale associe a ce foundedMaterielrielLocale
            Locale loadedLocale = localeRepository.findByReference(localDetails.getLocale().getReference());
            List<LocalDetails> foundedMaterielrielsLocle = loadedLocale.getLocalDetails();
            foundedMaterielrielsLocle.add(localDetails);
            loadedLocale.setLocalDetails(foundedMaterielrielsLocle);
            loadedLocale.setNbrMateriel(loadedLocale.getNbrMateriel() + 1);
            localeRepository.save(loadedLocale);

            //update foundedMaterielriel associe a ce foundedMaterielrielLocale
            Materiel loadedMateriel = foundedMaterielrielRepository.findByReference(localDetails.getMateriel().getReference());
            List<LocalDetails> foundedMaterielriels = loadedMateriel.getLocalDetails();
            foundedMaterielriels.add(localDetails);
            loadedMateriel.setLocalDetails(foundedMaterielriels);
            loadedMateriel.setNbrEntite(loadedMateriel.getNbrEntite() + 1);
            foundedMaterielrielRepository.save(loadedMateriel);

            //save du Nom LOcale et Nom foundedMaterielriel
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

        if (localDetails.getLocale() == null || localDetails.getLocale().getReference() == null) {
            return -1;
        } else if (foundedMaterielLoc != null && !(foundedMaterielLocale.getReferenceML().equals(localDetails.getReferenceML()))) {
            return -2;
        } else {
            // update du locale associe 
            if (!foundedMaterielLocale.getLocale().getReference().equals(localDetails.getLocale().getReference())) {

                //modifier lancien locale associe 
                List<LocalDetails> foundedMaterielrielslocaleToEdit = foundedMaterielLocale.getLocale().getLocalDetails();
                foundedMaterielrielslocaleToEdit.remove(foundedMaterielLocale);
                foundedMaterielLocale.getLocale().setLocalDetails(foundedMaterielrielslocaleToEdit);
                foundedMaterielLocale.getLocale().setNbrMateriel(foundedMaterielLocale.getLocale().getNbrMateriel() - 1);
                localeRepository.save(foundedMaterielLocale.getLocale());

                Locale loadedLocale = localeRepository.findByReference(localDetails.getLocale().getReference());
                if (loadedLocale != null) {
                    List<LocalDetails> foundedMaterielrielsLocle = loadedLocale.getLocalDetails();
                    foundedMaterielrielsLocle.add(localDetails);
                    loadedLocale.setLocalDetails(foundedMaterielrielsLocle);
                    loadedLocale.setNbrMateriel(loadedLocale.getNbrMateriel() + 1);
                    localeRepository.save(loadedLocale);
                }
                //update new Locale asoocie    
                foundedMaterielLocale.setLocale(loadedLocale);
            }

            //update du Materiel Associer
            Materiel loadedMateriel = foundedMaterielrielRepository.findByReference(localDetails.getMateriel().getReference());
            if (!foundedMaterielLocale.getMateriel().getReference().equals(localDetails.getMateriel().getReference())) {

                //modifier l' ancien Materiel associer
                List<LocalDetails> mats = foundedMaterielLocale.getMateriel().getLocalDetails();
                mats.remove(foundedMaterielLocale);
                foundedMaterielLocale.getMateriel().setLocalDetails(mats);
                foundedMaterielLocale.getMateriel().setNbrEntite(foundedMaterielLocale.getMateriel().getNbrEntite() - 1);
                foundedMaterielrielRepository.save(foundedMaterielLocale.getMateriel());

                //
                //update new Matriel associe
                foundedMaterielLocale.setMateriel(loadedMateriel);
                // list
                List<LocalDetails> foundedMaterielriels = loadedMateriel.getLocalDetails();
                foundedMaterielriels.add(localDetails);
                loadedMateriel.setLocalDetails(foundedMaterielriels);
                loadedMateriel.setNbrEntite(loadedMateriel.getNbrEntite() + 1);
                foundedMaterielrielRepository.save(loadedMateriel);
            }

            //update attributes of this MaterielLocale
            foundedMaterielLocale.setLocaleAssocie(localDetails.getLocale().getDescriptionDropDown());
            foundedMaterielLocale.setMaterielLocale(localDetails.getMateriel().getDescriptionDropDown());
            foundedMaterielLocale.setDateAffectation(localDetails.getDateAffectation());
            foundedMaterielLocale.setReferenceML(localDetails.getReferenceML());
            foundedMaterielLocale.setDescriptionMaterielLocale(localDetails.getReferenceML() + ", " + localDetails.getMaterielLocale());
            updatebeans(foundedMaterielLocale);
            localDetailsRepository.save(foundedMaterielLocale);
            
            

            
            return 1;
        }

    }

    public void updatebeans(LocalDetails foundedMateriel) {
        if (foundedMateriel.getPrestationInternes() != null) {
            List<PrestationInterne> pres = prestationInterneRepository.findAll();
            for (PrestationInterne pre : pres) {
                if(pre.getMaterielLocale() != null){
                if (pre.getMaterielLocale().getReference().equals(foundedMateriel.getReference())) {
                    pre.setNomMaterielI(foundedMateriel.getDescriptionMaterielLocale());
                    pre.setNomLocaleI(foundedMateriel.getLocaleAssocie());
                    prestationInterneRepository.save(pre);
                }
                }
            }
        }
        // update pres externe
        if (foundedMateriel.getPrestationExternes() != null) {
            List<PrestationExterne> prese = prestationExterneRepository.findAll();
            for (PrestationExterne pre : prese) {
                if(pre.getMaterielLocale() != null) {
                if (pre.getMaterielLocale().getReference().equals(foundedMateriel.getReference())) {
                    pre.setNomMateriel(foundedMateriel.getDescriptionMaterielLocale());
                    pre.setNomLocale(foundedMateriel.getLocaleAssocie());
                    prestationExterneRepository.save(pre);
                }
                }
            }
        }
        //
        if (foundedMateriel.getReclamations() != null) {
            List<Reclamation> recl = reclamationRepository.findAll();
            for (Reclamation rec : recl) {
                if(rec.getMateriel() != null) {
                if (rec.getMateriel().getReference().equals(foundedMateriel.getReference())) {
                    rec.setNomMateriel(foundedMateriel.getDescriptionMaterielLocale());
                    rec.setNomLocale(foundedMateriel.getLocaleAssocie());
                    reclamationRepository.save(rec);
                }
                }
            }
        }
        //setNomMateriel(localDetails.getReferenceML() + ", " + localDetails.getMaterielLocale());
        if (foundedMateriel.getLocale().getEntretiens() != null) {
            List<Entretien> entre = entretienRepository.findAll();
            for (Entretien ent : entre) {
                if(ent.getMateriel() != null){
                if (ent.getMateriel().getReference().equals(foundedMateriel.getReference())) {
                    ent.setNomMateriel(foundedMateriel.getDescriptionMaterielLocale());
                    ent.setNomLocale(foundedMateriel.getLocaleAssocie()); 
                    entretienRepository.save(ent);
                }
                }
            }
        }
        
    }

    @Override
    public int delete(String referenceMaterielLocal) {
        LocalDetails foundedMaterielLocale = localDetailsRepository.findByReference(referenceMaterielLocal);
        //Edit locale associer
        if (foundedMaterielLocale.getLocale() != null) {
            Locale loadedLocale = localeRepository.findByReference(foundedMaterielLocale.getLocale().getReference());
            List<LocalDetails> foundedMaterielrielslocaleToEdit = loadedLocale.getLocalDetails();
            foundedMaterielrielslocaleToEdit.remove(foundedMaterielLocale);
            loadedLocale.setLocalDetails(foundedMaterielrielslocaleToEdit);
            loadedLocale.setNbrMateriel(loadedLocale.getNbrMateriel() - 1);
            localeRepository.save(loadedLocale);
        }

        //Edit foundedMaterielriel asoocier
        if (foundedMaterielLocale.getMateriel() != null) {
            Materiel loadedMateriel = foundedMaterielrielRepository.findByReference(foundedMaterielLocale.getMateriel().getReference());
            List<LocalDetails> mats = loadedMateriel.getLocalDetails();
            mats.remove(foundedMaterielLocale);
            loadedMateriel.setLocalDetails(mats);
            loadedMateriel.setNbrEntite(foundedMaterielLocale.getMateriel().getNbrEntite() - 1);
            foundedMaterielrielRepository.save(loadedMateriel);
        }

        // list of prestations associate
        List<PrestationInterne> preIs = foundedMaterielLocale.getPrestationInternes();
        List<PrestationExterne> preEs = foundedMaterielLocale.getPrestationExternes();
        // set foundedMaterielriel to null
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
