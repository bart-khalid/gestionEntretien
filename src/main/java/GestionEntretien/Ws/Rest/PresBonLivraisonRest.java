/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Ws.Rest;

import GestionEntretien.Bean.PresBonLivraison;
import GestionEntretien.Service.PresBonLivraisonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lenovo
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("GestionEntretien/bonLivraison")
public class PresBonLivraisonRest {
    
    @Autowired
    private PresBonLivraisonService presBonLivraisonService;

    @GetMapping("/")
    public List<PresBonLivraison> findAll() {
        return presBonLivraisonService.findAll();
    }

    @DeleteMapping("/delete/{reference}")
    public int delete(@PathVariable String reference) {
        return presBonLivraisonService.delete(reference);
    }
    
    
}
