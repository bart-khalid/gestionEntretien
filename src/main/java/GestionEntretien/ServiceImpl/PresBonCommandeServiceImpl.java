/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.PresBonCommande;
import GestionEntretien.Dao.PresBonCommandeRepository;
import GestionEntretien.Service.PresBonCommandeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class PresBonCommandeServiceImpl implements PresBonCommandeService{

    @Autowired
    private PresBonCommandeRepository presBonCommandeRepository;
    
    @Override
    public List<PresBonCommande> findAll() {
        return presBonCommandeRepository.findAll();
    }

    @Override
    public int delete(String reference) {
        PresBonCommande foundedBonCommande = presBonCommandeRepository.findByReference(reference);
        presBonCommandeRepository.delete(foundedBonCommande);
        return 1;
    }
    
}
