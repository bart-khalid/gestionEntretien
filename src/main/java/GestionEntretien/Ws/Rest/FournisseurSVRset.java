/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Ws.Rest;

import GestionEntretien.Bean.Agent;
import GestionEntretien.Bean.FournisseurSV;
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
import GestionEntretien.Service.FournisseurSVService;

/**
 *
 * @author lenovo
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("GestionEntretien/fournisseur")
public class FournisseurSVRset {

    @Autowired
    private FournisseurSVService fournisseurService;

    @PostMapping("/")
    public int save(@RequestBody FournisseurSV fournisseur) {
        return fournisseurService.save(fournisseur);
    }

    @PutMapping("/update")
    public int update(@RequestBody FournisseurSV fournisseur) {
        return fournisseurService.update(fournisseur);
    }

    @DeleteMapping("/deleteFournisseur/{reference}")
    public int delete(@PathVariable String reference) {
        return fournisseurService.delete(reference);
    }

    @GetMapping("/")
    public List<FournisseurSV> findAll() {
        return fournisseurService.findAll();
    }

}
