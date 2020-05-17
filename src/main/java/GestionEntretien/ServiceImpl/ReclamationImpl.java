/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.LocalDetails;
import GestionEntretien.Bean.Users;
import GestionEntretien.Bean.Reclamation;
import GestionEntretien.Dao.LocalDetailsRepository;
import GestionEntretien.Dao.ReclamationRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import GestionEntretien.Service.ReclamationService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
// this import for random String generating
import org.apache.commons.lang3.RandomStringUtils;
import GestionEntretien.Dao.UsersRepository;

/**
 *
 * @author lenovo
 */
@Service
public class ReclamationImpl implements ReclamationService {

    @Autowired
    private ReclamationRepository reclamationRepository;

    @Autowired
    private UsersRepository loginRepository;
    
    @Autowired
    private LocalDetailsRepository localDetailsRepository;

    @Override
    public List<Reclamation> findAll() {
        return reclamationRepository.findAll();
    }

    @Override
    public int save(Reclamation reclamation, String username) {      
//        Reclamation foundedReclamation = reclamationRepository.findByReference(reclamation.getReference());
        Users foundedReclamant = loginRepository.findByUsername(username);
        
        
//        LocalDetails foundedMateriel = localDetailsRepository.findByReference(reclamation.getMateriel().getReference());



        if (foundedReclamant == null) {
            return -2;
//        } else if (foundedReclamation != null) {
//            return -1;
        } else {
            Reclamation.setNbr(Reclamation.getNbr()+1);
            reclamation.setReference(RandomStringUtils.random(6, true, false) + String.valueOf(Reclamation.getNbr()));
            reclamation.setEtat("Pas Encore Vu");
            reclamation.setReclamentName(foundedReclamant.getNom()+", "+ foundedReclamant.getPrenom());
            reclamation.setDate(new Date());
            reclamation.setReclament(foundedReclamant);
            //update reclamations of reclamant
            List<Reclamation> reclamationsUser = foundedReclamant.getReclamations();
            reclamationsUser.add(reclamation);
            foundedReclamant.setReclamations(reclamationsUser);
            loginRepository.save(foundedReclamant);
            
            // update reclamations of materiel
//            if(foundedMateriel != null) {
//                reclamation.setMateriel(foundedMateriel);
//                List<Reclamation> reclamationsMat = foundedMateriel.getReclamations();
//                reclamationsMat.add(reclamation);
//                foundedMateriel.setReclamations(reclamationsMat);
//                localDetailsRepository.save(foundedMateriel);
//            }
            reclamationRepository.save(reclamation);
           
            return 1;
        }

    }

    @Override
    public int reclamationSeen(String reference) {
        Reclamation foundedReclamation = reclamationRepository.findByReference(reference);
        if (foundedReclamation == null) {
            return -1;
           
        }else {
            foundedReclamation.setEtat("Sous Traitement");
            reclamationRepository.save(foundedReclamation);
            return 1;
        }
    }

}
