/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Ws.Rest;

import GestionEntretien.Bean.BonCarburant;
import GestionEntretien.Service.BonCarburantService;
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
@RequestMapping("GestionEntretien/boncarburant")
public class BonCarburantRest {

    @Autowired
    BonCarburantService boncservice;

    public BonCarburant findByReference(@PathVariable String reference) {
        return boncservice.findByReference(reference);
    }

    public BonCarburant findByNumbonC(@PathVariable String reference) {
        return boncservice.findByNumbonC(reference);
    }

    @PostMapping("/")
    public int save(@RequestBody BonCarburant boncarburant) {
        return boncservice.save(boncarburant);
    }

    @PutMapping("/update")
    public int update(@RequestBody BonCarburant boncarburant) {
        return boncservice.update(boncarburant);
    }

    @DeleteMapping("/delete/{reference}")
    public int delete(@PathVariable String reference) {
        return boncservice.delete(reference);
    }

    @GetMapping("/")
    public List<BonCarburant> findAll() {
        return boncservice.findAll();
    }

}
