/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.Entretien;
import GestionEntretien.Dao.EntretienRepository;
import GestionEntretien.Service.EntretienService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Zakaria
 */
@Service
public class EntretienServiceImpl implements EntretienService{

    @Autowired
    EntretienRepository entretienrepo;
    
    @Override
    public List<Entretien> findAll() {
    return entretienrepo.findAll();
    }

    @Override
    public int delete(String numfacture) {
    Entretien founded = entretienrepo.findByNumFacture(numfacture);
    entretienrepo.delete(founded);
        return 1;
    }

    @Override
    public Entretien findByNumFacture(String numfacture) {
    return entretienrepo.findByNumFacture(numfacture);
       }
    
}
