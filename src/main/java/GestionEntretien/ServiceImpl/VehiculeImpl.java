/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.Vehicule;
import GestionEntretien.Dao.VehiculeRepository;
import GestionEntretien.Service.VehiculeService;
import java.util.List;
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
        Vehicule foundedv = vehiculeRepository.findByMatricule(vehicule.getMatricule());
        if(foundedv!=null){
        return -1;
        }
        vehiculeRepository.save(vehicule);
        return 1;
    }

    @Override
    public int update(Vehicule vehicule) {
    Vehicule foundedv = vehiculeRepository.findByMatricule(vehicule.getMatricule());
    foundedv.setMatricule(vehicule.getMatricule());
    foundedv.setMarque(vehicule.getMarque());
    foundedv.setType(vehicule.getType());
    foundedv.setUtilite(vehicule.getUtilite());
    vehiculeRepository.save(foundedv);
    return 1;
    }

    @Override
    public int delete(String matricule) {
    Vehicule foundedv = vehiculeRepository.findByMatricule(matricule);
    if(foundedv == null){
    return -1;
    }
        vehiculeRepository.delete(foundedv);
        return 1;
    }

    @Override
    public List<Vehicule> findAll() {
        return vehiculeRepository.findAll();
    }
    
}
