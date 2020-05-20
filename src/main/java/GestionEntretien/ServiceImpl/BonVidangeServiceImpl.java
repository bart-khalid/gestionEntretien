/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.BonVidange;
import GestionEntretien.Dao.BonVidangeRepository;
import GestionEntretien.Service.BonVidangeService;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Zakaria
 */
@Service
public class BonVidangeServiceImpl implements BonVidangeService {
    @Autowired
    private BonVidangeRepository bonrepo;

    @Override
    public BonVidange findByReference(String reference) {
        return bonrepo.findByReference(reference);
    }

    @Override
    public BonVidange findByNumbonV(String numbonv) {
        return bonrepo.findByNumbonV(numbonv);
    }

    @Override
    public int save(BonVidange bonvidange) {
        BonVidange founded = bonrepo.findByNumbonV(bonvidange.getNumbonV());
        if (founded != null) {
            return -1;
        }
        BonVidange.setNbr(bonvidange.getNbr() + 1);
        bonvidange.setFourniassooci(bonvidange.getFournisseurV().getNomf()+" ,"+bonvidange.getFournisseurV().getAdressef());
        bonvidange.setVehiculeassooci(bonvidange.getVehiculeV().getMatricule()+" ,"+bonvidange.getVehiculeV().getMarque());
        bonvidange.setReference(RandomStringUtils.random(6, true, false) + String.valueOf(bonvidange.getNbr()));
        bonrepo.save(bonvidange);
        return 1;
    }

    @Override
    public int update(BonVidange bonvidange) {
      BonVidange founded = bonrepo.findByReference(bonvidange.getReference());
      founded.setFourniassooci(bonvidange.getFournisseurV().getNomf()+" ,"+bonvidange.getFournisseurV().getAdressef());
      founded.setVehiculeassooci(bonvidange.getVehiculeV().getMatricule()+" ,"+bonvidange.getVehiculeV().getMarque());
      founded.setNumbonV(bonvidange.getNumbonV());
      founded.setDatebonV(bonvidange.getDatebonV());
      founded.setDescriptionV(bonvidange.getDescriptionV());
      founded.setFournisseurV(bonvidange.getFournisseurV());
      founded.setVehiculeV(bonvidange.getVehiculeV());
      founded.setPrixunitaireV(bonvidange.getPrixunitaireV());
      founded.setTotalbrutV(bonvidange.getTotalbrutV());
      founded.setMontantvignetteV(bonvidange.getMontantvignetteV());
      founded.setKilometrageV(bonvidange.getKilometrageV());
      founded.setTypehuileV(bonvidange.getTypehuileV());
      founded.setQuantiteV(bonvidange.getQuantiteV());
      bonrepo.save(founded);
      return 1;
      
    }

    @Override
    public int delete(String reference) {
    BonVidange founded = bonrepo.findByReference(reference);
    bonrepo.delete(founded);
    return 1;
    }

    @Override
    public List<BonVidange> findAll() {
    return bonrepo.findAll();
    }
}
