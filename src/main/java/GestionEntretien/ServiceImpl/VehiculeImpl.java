/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.BonCarburant;
import GestionEntretien.Bean.BonReparation;
import GestionEntretien.Bean.BonVidange;
import GestionEntretien.Bean.Vehicule;
import GestionEntretien.Dao.BonCarburantRepository;
import GestionEntretien.Dao.BonReparationRepository;
import GestionEntretien.Dao.BonVidangeRepository;
import GestionEntretien.Dao.VehiculeRepository;
import GestionEntretien.Service.VehiculeService;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class VehiculeImpl implements VehiculeService {

    @Autowired
    private VehiculeRepository vehiculeRepository;
    @Autowired
    private BonCarburantRepository boncarrepo;
    @Autowired
    private BonReparationRepository bonreparepo;
    @Autowired
    private BonVidangeRepository bonvidrepo;

    @Override
    public int save(Vehicule vehicule) {
        Vehicule foundedv = vehiculeRepository.findByMatricule(vehicule.getMatricule());
        if (foundedv != null) {
            return -1;
        }
        vehicule.setDescriptionDropDown(vehicule.getMatricule() + ',' + vehicule.getMarque());
        Vehicule.setNbr(vehicule.getNbr() + 1);
        vehicule.setReference(RandomStringUtils.random(6, true, false) + String.valueOf(vehicule.getNbr()));
        vehiculeRepository.save(vehicule);
        return 1;
    }

    @Override
    public int update(Vehicule vehicule) {
        Vehicule foundedv = vehiculeRepository.findByReference(vehicule.getReference());
        foundedv.setDescriptionDropDown(vehicule.getMatricule() + ',' + vehicule.getMarque());
        foundedv.setMatricule(vehicule.getMatricule());
        foundedv.setMarque(vehicule.getMarque());
        foundedv.setType(vehicule.getType());
        foundedv.setUtilite(vehicule.getUtilite());
        foundedv.setDateEntrerParc(vehicule.getDateEntrerParc());
        List<BonCarburant> listBons = boncarrepo.findAll();
        List<BonReparation> listBonsrepa = bonreparepo.findAll();
        List<BonVidange> listBonsvid = bonvidrepo.findAll();
        for (BonCarburant bon : listBons) {
            if (bon.getVehiculeC().getReference().equals(foundedv.getReference())) {
                bon.setVehiculeassooci(foundedv.getDescriptionDropDown());
            }
            boncarrepo.save(bon);
        }
        for (BonReparation bon : listBonsrepa) {
            if (bon.getVehiculeR().getReference().equals(foundedv.getReference())) {
                bon.setVehiculeassooci(foundedv.getDescriptionDropDown());
            }
            bonreparepo.save(bon);
        }
        for (BonVidange bon : listBonsvid) {
            if (bon.getVehiculeV().getReference().equals(foundedv.getReference())) {
                bon.setVehiculeassooci(foundedv.getDescriptionDropDown());
            }
            bonvidrepo.save(bon);
        }
        vehiculeRepository.save(foundedv);
        return 1;
    }

    @Override
    public int delete(String reference) {
        Vehicule foundedv = vehiculeRepository.findByReference(reference);
        List<BonCarburant> listBons = boncarrepo.findAll();
        List<BonReparation> listBonsrepa = bonreparepo.findAll();
        List<BonVidange> listBonsvid = bonvidrepo.findAll();
        for (BonCarburant bon : listBons) {
            if (bon.getVehiculeC() == foundedv) {
                bon.setVehiculeC(null);
            }
            boncarrepo.save(bon);
        }
        for (BonReparation bon : listBonsrepa) {
            if (bon.getVehiculeR() == foundedv) {
                bon.setVehiculeR(null);
            }
            bonreparepo.save(bon);
        }
        for (BonVidange bon : listBonsvid) {
            if (bon.getVehiculeV() == foundedv) {
                bon.setVehiculeV(null);
            }
            bonvidrepo.save(bon);
        }
        vehiculeRepository.delete(foundedv);
        return 1;
    }

    @Override
    public List<Vehicule> findAll() {
        return vehiculeRepository.findAll();
    }

    @Override
    public Vehicule findByReference(String reference) {
        return vehiculeRepository.findByReference(reference);
    }

    @Override
    public Vehicule findByMatricule(String Matricule) {
        return vehiculeRepository.findByMatricule(Matricule);
    }

}
