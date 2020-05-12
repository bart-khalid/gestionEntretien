/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Ws.Rest;

import GestionEntretien.Bean.Agent;
import GestionEntretien.Bean.Fournisseur;
import GestionEntretien.Service.FournisseurService;
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
@RequestMapping("GestionEntretien/fournisseur")
public class FournisseurRset {
    @Autowired
    private FournisseurService fournisseurService;
    
    @PostMapping("/")
    public int save(@RequestBody Fournisseur fournisseur){
        return fournisseurService.save(fournisseur);
    }
    
    @PutMapping("/update")
    public int update(@RequestBody Fournisseur fournisseur){
        return fournisseurService.update(fournisseur);
    }
    
    @DeleteMapping("/deleteFournisseur")
    public int delete(@RequestBody Fournisseur fournisseur) {
        return fournisseurService.delete(fournisseur);
    }

    @GetMapping("/")
    public List<Fournisseur> findAll() {
        return fournisseurService.findAll();
    }
    
}
