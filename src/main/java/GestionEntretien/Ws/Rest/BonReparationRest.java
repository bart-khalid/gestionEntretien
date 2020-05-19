/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Ws.Rest;

import GestionEntretien.Bean.BonReparation;
import GestionEntretien.Service.BonReparationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Zakaria
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("GestionEntretien/bonreparation")
public class BonReparationRest {
     @Autowired
    BonReparationService bonrservice;

    
    public BonReparation findByReference(@PathVariable String reference) {
        return bonrservice.findByReference(reference);
    }

    public BonReparation findByNumbonR(@PathVariable String reference) {
        return bonrservice.findByNumbonR(reference);
    }

    @PostMapping("/")
    public int save(@RequestBody BonReparation boncarburant) {
        return bonrservice.save(boncarburant);
    }
    @PutMapping("/update")
    public int update(@RequestBody BonReparation boncarburant) {
        return bonrservice.update(boncarburant);
    }
    @DeleteMapping("/delete/{reference}")
    public int delete(@PathVariable String reference) {
        return bonrservice.delete(reference);
    }

    @GetMapping("/")
    public List<BonReparation> findAll() {
        return bonrservice.findAll();
    }
    
    
    
}
