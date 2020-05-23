/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Ws.Rest;

import GestionEntretien.Bean.Entretien;
import GestionEntretien.Service.EntretienService;
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
 * @author Zakaria
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("GestionEntretien/entretien")
public class EntretienRest {

    @Autowired
    EntretienService entretienservice;

    @GetMapping("/")
    public List<Entretien> findAll() {
        return entretienservice.findAll();
    }

    @DeleteMapping("/delete/{reference}")
    public int delete(@PathVariable String reference) {
        return entretienservice.delete(reference);
    }

}
