/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Ws.Rest;

import GestionEntretien.Bean.PrestationInterne;
import GestionEntretien.Service.PrestationInterneService;
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
@RequestMapping("GestionEntretien/prestationInterne")
public class PrestationInterneRest {

    @Autowired
    private PrestationInterneService prestationInterneService;

    @PostMapping("/")
    public int save(@RequestBody PrestationInterne preInterne) {
        return prestationInterneService.save(preInterne);
    }

    @PutMapping("/update")
    public int update(@RequestBody PrestationInterne preInterne) {
        return prestationInterneService.update(preInterne);
    }

    @DeleteMapping("/deletePrestationInterne/{reference}")
    public int delete(@PathVariable String reference) {
        return prestationInterneService.delete(reference);
    }

    @GetMapping("/")
    public List<PrestationInterne> findAll() {
        return prestationInterneService.findAll();
    }

}
