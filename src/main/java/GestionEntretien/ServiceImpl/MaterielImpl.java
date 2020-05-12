/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.Fournisseur;
import GestionEntretien.Bean.Materiel;
import GestionEntretien.Dao.MaterielRepository;
import GestionEntretien.Service.MaterielService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class MaterielImpl implements MaterielService{

    @Autowired
    private MaterielRepository materielRepository;
    
    
    @Override
    public int save(Materiel materiel) {
//        Fournisseur foundedFournisseur = materiel.getFournisseur();
//        //maj liste materiel of fournisseur
//        List<Materiel> materiels = foundedFournisseur.getMateriels();
//        materiels.add(materiel);
//        foundedFournisseur.setMateriels(materiels);
        

        materiel.setMarque(materiel.getFournisseur().getNom());
        materielRepository.save(materiel);
        return 1;
    }

    @Override
    public int update(Materiel materiel) {
        Materiel foundedMateriel = materielRepository.findByReference(materiel.getReference());
        
        foundedMateriel.setFournisseur(materiel.getFournisseur());
        foundedMateriel.setMarque(materiel.getMarque());
        foundedMateriel.setNom(materiel.getNom());
        foundedMateriel.setType(materiel.getType());
        
        return 1;
    }

    @Override
    public int deleteByReference(String reference) {
        Materiel foundedMateriel = materielRepository.findByReference(reference);
        materielRepository.delete(foundedMateriel);
        return 1;
    }

    @Override
    public List<Materiel> findAll() {
        return materielRepository.findAll();
    }
    
}
