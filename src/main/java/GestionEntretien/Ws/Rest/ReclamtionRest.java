/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Ws.Rest;

import GestionEntretien.Bean.Reclamation;
import GestionEntretien.Service.ReclamationService;
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
@RequestMapping("GestionEntretien/reclamation")
public class ReclamtionRest {
    @Autowired
    private ReclamationService reclamtionService;

   @GetMapping("/")
   public List<Reclamation> findAll(){
       return reclamtionService.findAll();
   }
   
   
   @PostMapping("/{username}")
   public int save(@RequestBody Reclamation reclamation,@PathVariable String username) {
       return reclamtionService.save(reclamation, username);
   }
   
   @GetMapping("/{reference}")
   public int reclamationSeen(@PathVariable String reference) {
       return reclamtionService.reclamationSeen(reference);
   }

   @PutMapping("/update")
    public int update(@RequestBody Reclamation reclamation) {
        return reclamtionService.update(reclamation);
    }

    @DeleteMapping("/deleteReclamation/{reference}")
    public int delete(@PathVariable String reference) {
        return reclamtionService.delete(reference);
    }
    
    @GetMapping("/reclamations")
    public List<Reclamation> findReclamationsNonTraiter(){
        return reclamtionService.findReclamationsNonTraiter();
    }
   
}
