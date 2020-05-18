/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.LocalDetails;
import GestionEntretien.Bean.Locale;
import GestionEntretien.Bean.Login;
import GestionEntretien.Bean.Reclamation;
import GestionEntretien.Dao.LocalDetailsRepository;
import GestionEntretien.Dao.LocaleRepository;
import GestionEntretien.Dao.LoginRepository;
import GestionEntretien.Dao.ReclamationRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import GestionEntretien.Service.ReclamationService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
// this import for random String generating
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author lenovo
 */
@Service
public class ReclamationImpl implements ReclamationService {

    @Autowired
    private ReclamationRepository reclamationRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private LocalDetailsRepository localDetailsRepository;
    
    @Autowired
    private LocaleRepository localeRepository;

    @Override
    public List<Reclamation> findAll() {
        return reclamationRepository.findAll();
    }

    @Override
    public int save(Reclamation reclamation, String username) {
        Reclamation foundedReclamation = reclamationRepository.findByReference(reclamation.getReference());
        Login foundedReclamant = loginRepository.findByUsername(username);
        LocalDetails foundedMateriel = localDetailsRepository.findByReferenceML(reclamation.getMateriel().getReferenceML());

        if (foundedReclamation != null) {
            return -1;
        } else if (foundedReclamant == null) {
            return -2;

        } else {
            Reclamation.setNbr(Reclamation.getNbr() + 1);
            reclamation.setReference(RandomStringUtils.random(6, true, false) + String.valueOf(Reclamation.getNbr()));
            reclamation.setEtat("Pas Encore Vu");
            reclamation.setReclamentName(foundedReclamant.getNom() + ", " + foundedReclamant.getPrenom());
            reclamation.setDate(new Date());
            reclamation.setNomLocale(reclamation.getLocale().getDescriptionDropDown());
            reclamation.setNomMateriel("Pas de materiel");
            reclamation.setReclament(foundedReclamant);
            //update reclamations of reclamant
            List<Reclamation> reclamationsUser = foundedReclamant.getReclamations();
            reclamationsUser.add(reclamation);
            foundedReclamant.setReclamations(reclamationsUser);
            loginRepository.save(foundedReclamant);

            //update reclamations du locale
            Locale foundedLocale = localeRepository.findByReference(reclamation.getLocale().getReference());
            List<Reclamation> reclams = foundedLocale.getReclamations();
            reclams.add(reclamation);
            foundedLocale.setReclamations(reclams);
            localeRepository.save(foundedLocale);
            
            
            // update reclamations of materiel
            if (foundedMateriel != null) {
                reclamation.setMateriel(foundedMateriel);
                List<Reclamation> reclamationsMat = foundedMateriel.getReclamations();
                reclamationsMat.add(reclamation);
                foundedMateriel.setReclamations(reclamationsMat);
                localDetailsRepository.save(foundedMateriel);
                
                reclamation.setNomMateriel(reclamation.getMateriel().getDescriptionMaterielLocale());
            } else {
                reclamation.setMateriel(foundedMateriel);
            }

            
            reclamation.setDescreptionDropDownReclamation(reclamation.getReference() + " " + reclamation.getObjet());
            reclamationRepository.save(reclamation);

            return 1;
        }

    }

    @Override
    public int update(Reclamation reclamation) {
        Reclamation foundedReclamation = reclamationRepository.findByReference(reclamation.getReference());
        LocalDetails loadedMateriel = reclamation.getMateriel();
        
        foundedReclamation.setObjet(reclamation.getObjet());
        foundedReclamation.setDescription(reclamation.getDescription());
        
        //update locale associe a cette reclamation
        if (!foundedReclamation.getLocale().getReference().equals(reclamation.getLocale().getReference())){
            foundedReclamation.setLocale(reclamation.getLocale());
        }
        
        if (loadedMateriel != null){
            if (!foundedReclamation.getMateriel().getReferenceML().equals(reclamation.getMateriel().getReferenceML())){
            foundedReclamation.setMateriel(reclamation.getMateriel());
            reclamation.setNomMateriel(reclamation.getMateriel().getDescriptionMaterielLocale());
            }
        }
        
        
        reclamation.setDescreptionDropDownReclamation(reclamation.getReference() + " " + reclamation.getObjet());
        reclamation.setNomLocale(reclamation.getLocale().getDescriptionDropDown());
        reclamationRepository.save(reclamation);
        return 1;
    }

    @Override
    public int delete (String refernce){
        Reclamation founReclamation = reclamationRepository.findByReference(refernce);
        if (founReclamation == null){
            return -1;
        }else {
            reclamationRepository.delete(founReclamation);
            return 1;
        }
    }
    @Override
    public int reclamationSeen(String reference) {
        Reclamation foundedReclamation = reclamationRepository.findByReference(reference);
        if (foundedReclamation == null) {
            return -1;

        } else {
            foundedReclamation.setEtat("Sous Traitement");
            reclamationRepository.save(foundedReclamation);
            return 1;
        }
    }

}
