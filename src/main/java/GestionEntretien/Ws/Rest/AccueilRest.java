/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Ws.Rest;

import GestionEntretien.Service.AccueilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lenovo
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("GestionEntretien/accueil")
public class AccueilRest {
    @Autowired
    private AccueilService accuieiService;

    @GetMapping("/sizePresIn")
    public int findAllPrestationInterne() {
        return accuieiService.findAllPrestationInterne();
    }

    @GetMapping("/sizePresExt")
    public int findAllPrestationExterne() {
        return accuieiService.findAllPrestationExterne();
    }

    @GetMapping("/sizeRecNonLu")
    public int findAllReclamationNonLu() {
        return accuieiService.findAllReclamationNonLu();
    }

    @GetMapping("/sizeRecSousTraite")
    public int findAllReclamationSousTraitement() {
        return accuieiService.findAllReclamationSousTraitement();
    }

    @GetMapping("/sizeRecBienTraite")
    public int findAllReclamationsBienTraite() {
        return accuieiService.findAllReclamationsBienTraite();
    }

    @GetMapping("/sizeBonCar")
    public int findAllBonCarburant() {
        return accuieiService.findAllBonCarburant();
    }

    @GetMapping("/sizeBonVid")
    public int findAllBonVidange() {
        return accuieiService.findAllBonVidange();
    }

    @GetMapping("/sizeBonRep")
    public int findAllBonReparation() {
        return accuieiService.findAllBonReparation();
    }

    @GetMapping("/sizeUsersAD")
    public int findAllUsersAdmin() {
        return accuieiService.findAllUsersAdmin();
    }

    @GetMapping("/sizeUsersEM")
    public int findAllUsersEmploye() {
        return accuieiService.findAllUsersEmploye();
    }

    @GetMapping("/sizeVehBus")
    public int findAllVehiculeAutoBus() {
        return accuieiService.findAllVehiculeAutoBus();
    }

    @GetMapping("/sizeVehVoi")
    public int findAllVehiculeVoiture() {
        return accuieiService.findAllVehiculeVoiture();
    }

    @GetMapping("/sizeMatInf")
    public int findAllMaterielInformatique() {
        return accuieiService.findAllMaterielInformatique();
    }

    @GetMapping("/sizeMatEn")
    public int findAllMaterielEnseinement() {
        return accuieiService.findAllMaterielEnseinement();
    }
    
}
