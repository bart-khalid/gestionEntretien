/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.Materiel;
import GestionEntretien.Bean.Reclamation;
import GestionEntretien.Bean.Users;
import GestionEntretien.Bean.Vehicule;
import GestionEntretien.Dao.BonCarburantRepository;
import GestionEntretien.Dao.BonReparationRepository;
import GestionEntretien.Dao.BonVidangeRepository;
import GestionEntretien.Dao.MaterielRepository;
import GestionEntretien.Dao.PresBonCommandeRepository;
import GestionEntretien.Dao.PresBonLivraisonRepository;
import GestionEntretien.Dao.PrestationExterneRepository;
import GestionEntretien.Dao.PrestationInterneRepository;
import GestionEntretien.Dao.ReclamationRepository;
import GestionEntretien.Dao.UsersRepository;
import GestionEntretien.Dao.VehiculeRepository;
import GestionEntretien.Service.AccueilService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class AccuieiImpl implements AccueilService {

    @Autowired
    private PrestationInterneRepository prestationInterneRepository;
    @Autowired
    private PrestationExterneRepository prestationExterneRepository;
    @Autowired
    private ReclamationRepository reclamationRepository;
    @Autowired
    private BonCarburantRepository bonCarburantRepository;
    @Autowired
    private BonVidangeRepository bonVidangeRepository;
    @Autowired
    private BonReparationRepository bonReparationRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private VehiculeRepository vehiculeRepository;
    @Autowired
    private MaterielRepository materielRepository;
    @Autowired
    private PresBonCommandeRepository presBonCommandeRepository;
    @Autowired
    private PresBonLivraisonRepository presBonLivraisonRepository;

    @Override
    public int findAllPrestationInterne() {
        return prestationInterneRepository.findAll().size();
    }

    @Override
    public int findAllPrestationExterne() {
        return prestationExterneRepository.findAll().size();
    }

    @Override
    public int findAllReclamationNonLu() {
        int size = 0;
        List<Reclamation> recs = reclamationRepository.findAll();
        for (Reclamation reclamation : recs) {
            if (reclamation.getEtat().equals("Pas Encore Vu")) {
                size++;
            }
        }
        return size;
    }

    @Override
    public int findAllReclamationSousTraitement() {
        int size = 0;
        List<Reclamation> recs = reclamationRepository.findAll();
        for (Reclamation reclamation : recs) {
            if (reclamation.getEtat().equals("Sous Traitement")) {
                size++;
            }
        }
        return size;
    }

    @Override
    public int findAllReclamationsBienTraite() {
        int size = 0;
        List<Reclamation> recs = reclamationRepository.findAll();
        for (Reclamation reclamation : recs) {
            if (reclamation.getEtat().equals("Bien Traitée")) {
                size++;
            }
        }
        return size;
    }

    @Override
    public int findAllBonCarburant() {
        return bonCarburantRepository.findAll().size();
    }

    @Override
    public int findAllBonVidange() {
        return bonVidangeRepository.findAll().size();
    }

    @Override
    public int findAllBonReparation() {
        return bonReparationRepository.findAll().size();
    }

    @Override
    public int findAllUsersAdmin() {
        int size = 0;
        List<Users> users = usersRepository.findAll();
        for (Users user : users) {
            if (user.getType().equals("administrateur")) {
                size++;
            }
        }
        return size;
    }

    @Override
    public int findAllUsersEmploye() {
        int size = 0;
        List<Users> users = usersRepository.findAll();
        for (Users user : users) {
            if (user.getType().equals("employé")) {
                size++;
            }
        }
        return size;
    }

    @Override
    public int findAllVehiculeAutoBus() {
        int size = 0;
        List<Vehicule> vehiculs = vehiculeRepository.findAll();
        for (Vehicule vehicul : vehiculs) {
            if (vehicul.getType().equals("Bus")) {
                size++;
            }
        }
        return size;
    }

    @Override
    public int findAllVehiculeVoiture() {
        int size = 0;
        List<Vehicule> vehiculs = vehiculeRepository.findAll();
        for (Vehicule vehicul : vehiculs) {
            if (vehicul.getType().equals("Automobile")) {
                size++;
            }
        }
        return size;
    }

    @Override
    public int findAllMaterielInformatique() {
        int size = 0;
        List<Materiel> materiels = materielRepository.findAll();
        for (Materiel materiel : materiels) {
            if (materiel.getType().equals("Informatique")) {
                size += materiel.getLocalDetails().size();
            }
        }
        return size;
    }

    @Override
    public int findAllMaterielEnseinement() {
        int size = 0;
        List<Materiel> materiels = materielRepository.findAll();
        for (Materiel materiel : materiels) {
            if (materiel.getType().equals("Enseignement")) {
                size += materiel.getLocalDetails().size();
            }
        }
        return size;
    }

    @Override
    public int findAllBonCommande() {
        return presBonCommandeRepository.findAll().size();
    }

    @Override
    public int findAllBonLivraison() {
        return presBonLivraisonRepository.findAll().size();
    }

}
