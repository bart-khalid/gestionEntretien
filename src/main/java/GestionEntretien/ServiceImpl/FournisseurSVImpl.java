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
import GestionEntretien.Bean.Materiel;
import GestionEntretien.Dao.BonCarburantRepository;
import GestionEntretien.Dao.BonReparationRepository;
import GestionEntretien.Dao.BonVidangeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import GestionEntretien.Dao.FournisseurSVRepository;
import GestionEntretien.Dao.MaterielRepository;
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
    @Autowired
    private MaterielRepository materielRepository;

    @Override
    public int save(FournisseurSV fournisseur) {

        FournisseurSV foundedtele = fournisseurRepository.findByTelephonef(fournisseur.getTelephonef());
        FournisseurSV foundedmail = fournisseurRepository.findByEmailf(fournisseur.getEmailf());

        if (foundedtele != null) {
            return -2;
        } else if (foundedmail != null) {
            return -3;
        } else {
            fournisseur.setDescriptionDropDown(fournisseur.getNomf() + ',' + fournisseur.getAdressef());
            FournisseurSV.setNbr(fournisseur.getNbr() + 1);
            fournisseur.setReference(RandomStringUtils.random(6, true, false) + String.valueOf(fournisseur.getNbr()));
            FournisseurSV foundedFournisseur = fournisseurRepository.findByReference(fournisseur.getReference());
            while (foundedFournisseur != null) {
                FournisseurSV.setNbr(fournisseur.getNbr() + 1);
                fournisseur.setReference(RandomStringUtils.random(6, true, false) + String.valueOf(fournisseur.getNbr()));
                foundedFournisseur = fournisseurRepository.findByReference(fournisseur.getReference());
            }
            fournisseurRepository.save(fournisseur);
            return 1;
        }

    }

    @Override
    public int update(FournisseurSV fournisseur) {
        FournisseurSV foundedFournisseur = fournisseurRepository.findByReference(fournisseur.getReference());

        // check mail
        if (!(foundedFournisseur.getEmailf().equals(fournisseur.getEmailf()))) {
            FournisseurSV foundedlogin = findByEmailf(fournisseur.getEmailf());
            if (foundedlogin != null) {
                return -1;
            } else {
                if (!foundedFournisseur.getReference().equals(fournisseur.getReference())) {
                    foundedFournisseur.setEmailf(fournisseur.getEmailf());
                }
            }
        } else {
            foundedFournisseur.setEmailf(fournisseur.getEmailf());
        }

        //check tele
        if (!(foundedFournisseur.getTelephonef().equals(fournisseur.getTelephonef()))) {
            FournisseurSV foundedlogin = findByTelephonef(fournisseur.getTelephonef());
            if (foundedlogin != null) {
                return -2;
            } else {
                if (foundedFournisseur.getReference().equals(fournisseur.getReference())) {
                    foundedFournisseur.setTelephonef(fournisseur.getTelephonef());
                }
            }
        } else {
            foundedFournisseur.setTelephonef(fournisseur.getTelephonef());
        }

        foundedFournisseur.setDescriptionDropDown(fournisseur.getNomf() + ',' + fournisseur.getAdressef());
        foundedFournisseur.setNomf(fournisseur.getNomf());
        foundedFournisseur.setAdressef(fournisseur.getAdressef());
        foundedFournisseur.setTypef(fournisseur.getTypef());

        List<BonCarburant> listBons = boncarrepo.findAll();
        List<BonReparation> listBonsrepa = bonreparepo.findAll();
        List<BonVidange> listBonsvid = bonvidrepo.findAll();
        List<Materiel> listmat = materielRepository.findAll();
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

        for (Materiel mat : listmat) {
            if (mat.getFournisseur().getReference().equals(foundedFournisseur.getReference())) {
                mat.setDescriptionDropDown(foundedFournisseur.getDescriptionDropDown());
            }
            materielRepository.save(mat);
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
        List<BonCarburant> listBons = boncarrepo.findAll();
        List<BonReparation> listBonsrepa = bonreparepo.findAll();
        List<BonVidange> listBonsvid = bonvidrepo.findAll();
        List<Materiel> listmat = materielRepository.findAll();
        for (BonCarburant bon : listBons) {
            if (bon.getFournisseurC() == foundedFournisseur) {
                bon.setFournisseurC(null);
            }
            boncarrepo.save(bon);
        }
        for (BonReparation bon : listBonsrepa) {
            if (bon.getFournisseurR() == foundedFournisseur) {
                bon.setFournisseurR(null);
            }
            bonreparepo.save(bon);
        }
        for (BonVidange bon : listBonsvid) {
            if (bon.getFournisseurV() == foundedFournisseur) {
                bon.setFournisseurV(null);
            }
            bonvidrepo.save(bon);
        }
        for (Materiel mat : listmat) {
            if (mat.getFournisseur() == foundedFournisseur) {
                mat.setFournisseur(null);
            }
            materielRepository.save(mat);
        }

        fournisseurRepository.delete(foundedFournisseur);
        return 1;
    }

    @Override
    public FournisseurSV findByReference(String reference) {
        return fournisseurRepository.findByReference(reference);
    }

    @Override
    public FournisseurSV findByTelephonef(String tele) {
        return fournisseurRepository.findByTelephonef(tele);
    }

    @Override
    public FournisseurSV findByEmailf(String mail) {
        return fournisseurRepository.findByEmailf(mail);
    }

}
