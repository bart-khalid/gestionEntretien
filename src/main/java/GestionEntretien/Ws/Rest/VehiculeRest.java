/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Ws.Rest;

import GestionEntretien.Bean.Vehicule;
import GestionEntretien.Service.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lenovo
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("GestionEntretien/vehicule")
public class VehiculeRest {
    
    @Autowired
    private VehiculeService vehiculeService;
    
    @PostMapping("/")
    public int save(@RequestBody Vehicule vehicule){
        return vehiculeService.save(vehicule);
    }
    
    @PutMapping("/update")
    public int update(@RequestBody Vehicule vehicule){
        return vehiculeService.update(vehicule);
    }
    
    @DeleteMapping("/deleteVehicule")
    public int dalete(@RequestBody Vehicule vehicule){
        return vehiculeService.delete(vehicule);
    }
}
