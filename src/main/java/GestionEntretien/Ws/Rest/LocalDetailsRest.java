/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Ws.Rest;

import GestionEntretien.Bean.LocalDetails;
import GestionEntretien.Service.LocalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("GestionEntretien/materielsLocale")
public class LocalDetailsRest {
    @Autowired
   LocalDetailsService localDetailsService;
    
    
    @PostMapping("/")
    public int save(@RequestBody LocalDetails localDetails){
        return localDetailsService.save(localDetails);
    }
    
    
    @PutMapping("/update")
    public int update(@RequestBody LocalDetails localDetails){
        return localDetailsService.update(localDetails);
    }
    
    @DeleteMapping("/deleteMateriel/{reference}")
    public int delete(@PathVariable String referenceMaterielLocal){
        return localDetailsService.delete(referenceMaterielLocal);
    }
}
