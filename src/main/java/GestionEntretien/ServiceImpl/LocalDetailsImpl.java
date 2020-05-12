/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.LocalDetails;
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
        //save du materielLocale
        localDetails.setLocaleAssocie(localDetails.getLocale().getNom() + ", " + localDetails.getLocale().getDepartement());
        localDetails.setMaterielLocale(localDetails.getMateriel().getNom() + ", " + localDetails.getMateriel().getMarque());
        localDetailsRepository.save(localDetails);

        //update locale associe a ce materielLocale
        List<LocalDetails> materielsLocle = localDetails.getLocale().getLocalDetails();
        materielsLocle.add(localDetails);
        localDetails.getLocale().setLocalDetails(materielsLocle);
        localeRepository.save(localDetails.getLocale());

        //update materiel associe a ce materielLocale
        List<LocalDetails> materiels = localDetails.getMateriel().getLocalDetails();
        materiels.add(localDetails);
        localDetails.getMateriel().setLocalDetails(materiels);
        localDetails.getMateriel().setNbrEntite(localDetails.getMateriel().getNbrEntite() + 1);
        materielRepository.save(localDetails.getMateriel());

        return 1;
    }

    @Override
    public int update(LocalDetails localDetails) {
        LocalDetails foundedMaterielLocale = localDetailsRepository.findByReference(localDetails.getReference());

        //update reference
        foundedMaterielLocale.setReference(localDetails.getReference());

        // update du locale associe 
        if (foundedMaterielLocale.getLocale().getReference() != localDetails.getLocale().getReference()) {

            //modifier lancien locale associe 
            List<LocalDetails> materielslocaleToEdit = foundedMaterielLocale.getLocale().getLocalDetails();
            materielslocaleToEdit.remove(localDetails);
            foundedMaterielLocale.getLocale().setLocalDetails(materielslocaleToEdit);
            localeRepository.save(foundedMaterielLocale.getLocale());

            //update new Locale asoocie    
            foundedMaterielLocale.setLocale(localDetails.getLocale());
            //
            List<LocalDetails> materielsLocle = localDetails.getLocale().getLocalDetails();
            materielsLocle.add(localDetails);
            localDetails.getLocale().setLocalDetails(materielsLocle);
            localeRepository.save(localDetails.getLocale());
        }

        //update du Materiel Associer
        if (foundedMaterielLocale.getMateriel().getReference() != localDetails.getMateriel().getReference()) {

            //modifier l' ancien Materiel associer
            List<LocalDetails> mats = foundedMaterielLocale.getMateriel().getLocalDetails();
            mats.remove(localDetails);
            foundedMaterielLocale.getMateriel().setLocalDetails(mats);
            foundedMaterielLocale.getMateriel().setNbrEntite(foundedMaterielLocale.getMateriel().getNbrEntite() - 1);
            materielRepository.save(foundedMaterielLocale.getMateriel());

            //update new Matriel associe
            foundedMaterielLocale.setMateriel(localDetails.getMateriel());
            //
            List<LocalDetails> materiels = localDetails.getMateriel().getLocalDetails();
            materiels.add(localDetails);
            localDetails.getMateriel().setLocalDetails(materiels);
            localDetails.getMateriel().setNbrEntite(localDetails.getMateriel().getNbrEntite() + 1);
            materielRepository.save(localDetails.getMateriel());
        }

        //update attributes of this MaterielLocale
        localDetails.setLocaleAssocie(localDetails.getLocale().getNom() + ", " + localDetails.getLocale().getDepartement());
        localDetails.setMaterielLocale(localDetails.getMateriel().getNom() + ", " + localDetails.getMateriel().getMarque());
        localDetailsRepository.save(localDetails);

        return 1;
    }

    @Override
    public int delete(String referenceMaterielLocal) {
        LocalDetails foundedMaterielLocale = localDetailsRepository.findByReference(referenceMaterielLocal);
        //Edit locale associer
        List<LocalDetails> materielslocaleToEdit = foundedMaterielLocale.getLocale().getLocalDetails();
        materielslocaleToEdit.remove(foundedMaterielLocale);
        foundedMaterielLocale.getLocale().setLocalDetails(materielslocaleToEdit);
        localeRepository.save(foundedMaterielLocale.getLocale());

        //Edit materiel asoocier
        List<LocalDetails> mats = foundedMaterielLocale.getMateriel().getLocalDetails();
        mats.remove(foundedMaterielLocale);
        foundedMaterielLocale.getMateriel().setLocalDetails(mats);
        foundedMaterielLocale.getMateriel().setNbrEntite(foundedMaterielLocale.getMateriel().getNbrEntite() - 1);
        materielRepository.save(foundedMaterielLocale.getMateriel());

       
        //delete this localeMateriels
        localDetailsRepository.delete(foundedMaterielLocale);
        return 1;
    }

    @Override
    public List<LocalDetails> findAll() {
        return localDetailsRepository.findAll();
    }

}
