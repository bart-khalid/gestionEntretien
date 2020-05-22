/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.LocalDetails;
import GestionEntretien.Bean.Locale;
import GestionEntretien.Bean.Materiel;
import GestionEntretien.Dao.LocalDetailsRepository;
import GestionEntretien.Dao.LocaleRepository;
import GestionEntretien.Dao.MaterielRepository;
import GestionEntretien.Service.LocalDetailsService;
import java.util.List;
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

    @Override
    public int save(LocalDetails localDetails) {
        LocalDetails foundedMaterielLocal = localDetailsRepository.findByReferenceML(localDetails.getReferenceML());
        if (foundedMaterielLocal != null) {
            return -1;
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
            localDetails.setDescriptionMaterielLocale(localDetails.getReferenceML()+ ", " + localDetails.getMaterielLocale() );
            localDetailsRepository.save(localDetails);
            return 1;
        }
    }

    @Override
    public int update(LocalDetails localDetails) {
        LocalDetails foundedMaterielLocale = localDetailsRepository.findByReferenceML(localDetails.getReferenceML());

        //update reference
        foundedMaterielLocale.setReferenceML(localDetails.getReferenceML());

        // update du locale associe 
        if (!foundedMaterielLocale.getLocale().getReference().equals(localDetails.getLocale().getReference())) {

            //modifier lancien locale associe 
            List<LocalDetails> materielslocaleToEdit = foundedMaterielLocale.getLocale().getLocalDetails();
            materielslocaleToEdit.remove(foundedMaterielLocale);
            foundedMaterielLocale.getLocale().setLocalDetails(materielslocaleToEdit);
            foundedMaterielLocale.getLocale().setNbrMateriel(foundedMaterielLocale.getLocale().getNbrMateriel() - 1);
            localeRepository.save(foundedMaterielLocale.getLocale());

            //update new Locale asoocie    
            foundedMaterielLocale.setLocale(localDetails.getLocale());
            //
//            List<LocalDetails> materielsLocle = localDetails.getLocale().getLocalDetails();
//            materielsLocle.add(localDetails);
//            localDetails.getLocale().setLocalDetails(materielsLocle);
//            localDetails.getLocale().setNbrMateriel(localDetails.getLocale().getNbrMateriel() + 1);
//            localeRepository.save(localDetails.getLocale());
            
            Locale loadedLocale = localeRepository.findByReference(localDetails.getLocale().getReference());
            List<LocalDetails> materielsLocle = loadedLocale.getLocalDetails();
            materielsLocle.add(localDetails);
            loadedLocale.setLocalDetails(materielsLocle);
            loadedLocale.setNbrMateriel(loadedLocale.getNbrMateriel() + 1);
            localeRepository.save(loadedLocale);
        }

        //update du Materiel Associer
        if (!foundedMaterielLocale.getMateriel().getReference().equals(localDetails.getMateriel().getReference())) {

            //modifier l' ancien Materiel associer
            List<LocalDetails> mats = foundedMaterielLocale.getMateriel().getLocalDetails();
            mats.remove(foundedMaterielLocale);
            foundedMaterielLocale.getMateriel().setLocalDetails(mats);
            foundedMaterielLocale.getMateriel().setNbrEntite(foundedMaterielLocale.getMateriel().getNbrEntite() - 1);
            materielRepository.save(foundedMaterielLocale.getMateriel());

            //update new Matriel associe
            foundedMaterielLocale.setMateriel(localDetails.getMateriel());
            //
//            List<LocalDetails> materiels = localDetails.getMateriel().getLocalDetails();
//            materiels.add(localDetails);
//            localDetails.getMateriel().setLocalDetails(materiels);
//            localDetails.getMateriel().setNbrEntite(localDetails.getMateriel().getNbrEntite() + 1);
//            materielRepository.save(localDetails.getMateriel());
            
            Materiel loadedMateriel = materielRepository.findByReference(localDetails.getMateriel().getReference());
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
        
        localDetails.setDescriptionMaterielLocale(localDetails.getReferenceML()+ ", " + localDetails.getMaterielLocale() );
        localDetailsRepository.save(foundedMaterielLocale);

        return 1;
    }

    @Override
    public int delete(String referenceMaterielLocal) {
        LocalDetails foundedMaterielLocale = localDetailsRepository.findByReferenceML(referenceMaterielLocal);
        //Edit locale associer
        Locale loadedLocale = localeRepository.findByReference(foundedMaterielLocale.getLocale().getReference());
        List<LocalDetails> materielslocaleToEdit = loadedLocale.getLocalDetails();
        materielslocaleToEdit.remove(foundedMaterielLocale);
        loadedLocale.setLocalDetails(materielslocaleToEdit);
        loadedLocale.setNbrMateriel(loadedLocale.getNbrMateriel() - 1);
        localeRepository.save(loadedLocale);

        //Edit materiel asoocier
        Materiel loadedMateriel = materielRepository.findByReference(foundedMaterielLocale.getMateriel().getReference());
        List<LocalDetails> mats = loadedMateriel.getLocalDetails();
        mats.remove(foundedMaterielLocale);
        loadedMateriel.setLocalDetails(mats);
        loadedMateriel.setNbrEntite(foundedMaterielLocale.getMateriel().getNbrEntite() - 1);
        materielRepository.save(loadedMateriel);

        //delete this localeMateriels
        localDetailsRepository.delete(foundedMaterielLocale);
        return 1;
    }

    @Override
    public List<LocalDetails> findAll() {
        return localDetailsRepository.findAll();
    }
}
