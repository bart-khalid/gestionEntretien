/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Ws.Rest;

import GestionEntretien.Bean.Materiel;
import GestionEntretien.Service.MaterielService;
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
 * @author lenovo
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("GestionEntretien/materiel")
public class MaterielRest {
    
     @Autowired
    private MaterielService materielService;
    
    @PostMapping("/")
    public int save(@RequestBody Materiel materiel){
        return materielService.save(materiel);
    }
    
    @PutMapping("/update")
    public int update(@RequestBody Materiel materiel){
        return materielService.update(materiel);
    }
    
    @DeleteMapping("/deleteMateriel/{reference}")
    public int deleteByReference(@PathVariable String reference){
        return materielService.deleteByReference(reference);
    }

    @GetMapping("/")
    public List<Materiel> findAll() {
        return materielService.findAll();
    }
    
    
}
