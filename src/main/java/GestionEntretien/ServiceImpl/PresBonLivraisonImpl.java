/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.PresBonLivraison;
import GestionEntretien.Dao.PresBonLivraisonRepository;
import GestionEntretien.Service.PresBonLivraisonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class PresBonLivraisonImpl implements PresBonLivraisonService{

    @Autowired
    private PresBonLivraisonRepository presBonLivraisonRepository;
    
    
    @Override
    public List<PresBonLivraison> findAll() {
        return presBonLivraisonRepository.findAll();
    }

    @Override
    public int delete(String reference) {
        PresBonLivraison foundedPresBonLivraison = presBonLivraisonRepository.findByReference(reference);
        presBonLivraisonRepository.delete(foundedPresBonLivraison);
        return 1;
    }
    
}
