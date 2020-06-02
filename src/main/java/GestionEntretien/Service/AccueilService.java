/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Service;

/**
 *
 * @author lenovo
 */
public interface AccueilService {
    public int findAllPrestationInterne();
    public int findAllPrestationExterne();
    public int findAllReclamationNonLu();
    public int findAllReclamationSousTraitement();
    public int findAllReclamationsBienTraite();
    public int findAllBonCarburant();
    public int findAllBonVidange();
    public int findAllBonReparation();
    public int findAllUsersAdmin();
    public int findAllUsersEmploye();
    public int findAllVehiculeAutoBus();
    public int findAllVehiculeVoiture();
    public int findAllMaterielInformatique();
    public int findAllMaterielEnseinement();
    public int findAllBonCommande();
    public int findAllBonLivraison();
}
