/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.Vehicule;
import GestionEntretien.Dao.VehiculeRepository;
import GestionEntretien.Service.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class VehiculeImpl implements VehiculeService{

    @Autowired
    private VehiculeRepository vehiculeRepository;
    
    @Override
    public int save(Vehicule vehicule) {
        vehiculeRepository.save(vehicule);
        return 1;
    }

    @Override
    public int update(Vehicule vehicule) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Vehicule vehicule) {
        vehiculeRepository.delete(vehicule);
        return 1;
    }
    
}
