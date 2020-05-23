/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.PrestationExterne;
import GestionEntretien.Dao.PrestationExterneRepository;
import GestionEntretien.Service.PrestationExterneService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class PrestationExterneImpl implements PrestationExterneService{
    @Autowired
    private PrestationExterneRepository prestationExterneRepository;

    @Override
    public int save(PrestationExterne prestationExterne) {
        if (prestationExterne.getLocale().getReference() == null) {
            return -1;
        }else if(prestationExterne.getMaterielLocale().getReferenceML() == null && prestationExterne.getTypeEntretienE().equals("materiel")) {
            return -2;
        }else {
            
             return 1;
        }
    }

    @Override
    public int update(PrestationExterne prestationExterne) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(String reference) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PrestationExterne> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
