/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Ws.Rest;

/**
 *
 * @author lenovo
 */


import GestionEntretien.Bean.PresBonCommande;
import GestionEntretien.Service.PresBonCommandeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("GestionEntretien/bonCommande")
public class PresBonCommandeRest {
    
    @Autowired
    private PresBonCommandeService presBonCommandeService;

    @GetMapping("/")
    public List<PresBonCommande> findAll() {
        return presBonCommandeService.findAll();
    }

    @DeleteMapping("/delete/{reference}")
    public int delete(@PathVariable String reference) {
        return presBonCommandeService.delete(reference);
    }
    
    
}
