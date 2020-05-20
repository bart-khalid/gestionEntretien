/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Ws.Rest;

import GestionEntretien.Bean.BonVidange;
import GestionEntretien.Service.BonVidangeService;
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
@RequestMapping("GestionEntretien/bonvidange")
public class BonVidangeRest {
       @Autowired
       BonVidangeService bonvservice;

    @GetMapping("/find/ref/{reference}")
    public BonVidange findByReference(@PathVariable String reference) {
        return bonvservice.findByReference(reference);
    }

    @GetMapping("/find/{numbonv}")
    public BonVidange findByNumbonV(@PathVariable String numbonv) {
        return bonvservice.findByNumbonV(numbonv);
    }

    @PostMapping("/")
    public int save(@RequestBody BonVidange boncarburant) {
        return bonvservice.save(boncarburant);
    }
    @PutMapping("/update")
    public int update(@RequestBody BonVidange boncarburant) {
        return bonvservice.update(boncarburant);
    }
    @DeleteMapping("/delete/{reference}")
    public int delete(@PathVariable String reference) {
        return bonvservice.delete(reference);
    }

    @GetMapping("/")
    public List<BonVidange> findAll() {
        return bonvservice.findAll();
    }
}
