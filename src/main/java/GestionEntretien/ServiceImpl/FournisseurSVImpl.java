/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.BonCarburant;
import GestionEntretien.Bean.BonReparation;
import GestionEntretien.Bean.BonVidange;
import GestionEntretien.Bean.FournisseurSV;
import GestionEntretien.Dao.BonCarburantRepository;
import GestionEntretien.Dao.BonReparationRepository;
import GestionEntretien.Dao.BonVidangeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import GestionEntretien.Dao.FournisseurSVRepository;
import GestionEntretien.Service.FournisseurSVService;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author lenovo
 */
@Service
public class FournisseurSVImpl implements FournisseurSVService {

    @Autowired
    private FournisseurSVRepository fournisseurRepository;
    @Autowired
    private BonCarburantRepository boncarrepo;
    @Autowired
    private BonReparationRepository bonreparepo;
    @Autowired
    private BonVidangeRepository bonvidrepo;

    @Override
    public int save(FournisseurSV fournisseur) {
        FournisseurSV foundedFournisseur = fournisseurRepository.findByNomfAndAdressef(fournisseur.getNomf(), fournisseur.getAdressef());
        if (foundedFournisseur != null) {
            return -1;
        } else {
            fournisseur.setDescriptionDropDown(fournisseur.getNomf() + ',' + fournisseur.getAdressef());
            FournisseurSV.setNbr(fournisseur.getNbr() + 1);
            fournisseur.setReference(RandomStringUtils.random(6, true, false) + String.valueOf(fournisseur.getNbr()));
            fournisseurRepository.save(fournisseur);
            return 1;
        }

    }

    @Override
    public int update(FournisseurSV fournisseur) {
        FournisseurSV foundedFournisseur = fournisseurRepository.findByReference(fournisseur.getReference());
        foundedFournisseur.setDescriptionDropDown(fournisseur.getNomf() + ',' + fournisseur.getAdressef());
        foundedFournisseur.setNomf(fournisseur.getNomf());
        foundedFournisseur.setAdressef(fournisseur.getAdressef());
        foundedFournisseur.setEmailf(fournisseur.getEmailf());
        foundedFournisseur.setTelephonef(fournisseur.getTelephonef());
        foundedFournisseur.setTypef(fournisseur.getTypef());

        List<BonCarburant> listBons = boncarrepo.findAll();
        List<BonReparation> listBonsrepa = bonreparepo.findAll();
        List<BonVidange> listBonsvid = bonvidrepo.findAll();
        for (BonCarburant bon : listBons) {
            if (bon.getFournisseurC().getReference().equals(foundedFournisseur.getReference())) {
                bon.setFourniassooci(foundedFournisseur.getDescriptionDropDown());
            }
            boncarrepo.save(bon);
        }
        for (BonReparation bon : listBonsrepa) {
            if (bon.getFournisseurR().getReference().equals(foundedFournisseur.getReference())) {
                bon.setFourniassooci(foundedFournisseur.getDescriptionDropDown());
            }
            bonreparepo.save(bon);
        }
        for (BonVidange bon : listBonsvid) {
            if (bon.getFournisseurV().getReference().equals(foundedFournisseur.getReference())) {
                bon.setFourniassooci(foundedFournisseur.getDescriptionDropDown());
            }
            bonvidrepo.save(bon);
        }

        fournisseurRepository.save(foundedFournisseur);
        return 1;
    }

    @Override
    public List<FournisseurSV> findAll() {
        return fournisseurRepository.findAll();
    }

    @Override
    public int delete(String reference) {
        FournisseurSV foundedFournisseur = fournisseurRepository.findByReference(reference);
        fournisseurRepository.delete(foundedFournisseur);
        return 1;
    }

    @Override
    public FournisseurSV findByReference(String reference) {
        return fournisseurRepository.findByReference(reference);
    }

}
