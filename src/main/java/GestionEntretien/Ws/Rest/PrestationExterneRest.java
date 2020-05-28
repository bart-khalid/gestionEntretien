/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Ws.Rest;

import GestionEntretien.Bean.PrestationExterne;
import GestionEntretien.Service.PrestationExterneService;
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
@RequestMapping("GestionEntretien/prestationExterne")
public class PrestationExterneRest {
    @Autowired
    private PrestationExterneService prestationExterneService;

    @PostMapping("/")
    public int save(@RequestBody PrestationExterne prestationExterne) {
        return prestationExterneService.save(prestationExterne);
    }

    @PutMapping("/update")
    public int update(@RequestBody PrestationExterne prestationExterne) {
        return prestationExterneService.update(prestationExterne);
    }

    @DeleteMapping("/delete/{reference}")
    public int delete(@PathVariable String reference) {
        return prestationExterneService.delete(reference);
    }

    @GetMapping("/")
    public List<PrestationExterne> findAll() {
        return prestationExterneService.findAll();
    }
    
    
}
