/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.Fournisseur;
import GestionEntretien.Dao.FournisseurRepository;
import GestionEntretien.Service.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class FournisseurImpl implements FournisseurService{

    @Autowired
    private FournisseurRepository fournisseurRepository;
    
    
    
    @Override
    public int save(Fournisseur fournisseur) {
        fournisseurRepository.save(fournisseur);
        return 1;
    }

    @Override
    public int update(Fournisseur fournisseur) {
        return 1;
    }

    @Override
    public int delete(Fournisseur fournisseur) {
        fournisseurRepository.delete(fournisseur);
        return 1;
    }
    
}
