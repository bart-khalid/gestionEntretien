/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.BonCarburant;
import GestionEntretien.Dao.BonCarburantRepository;
import GestionEntretien.Service.BonCarburantService;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Zakaria
 */
@Service
public class BonCarburantServiceImpl implements BonCarburantService {
    @Autowired
    private BonCarburantRepository bonrepo;

    @Override
    public BonCarburant findByReference(String reference) {
        return bonrepo.findByReference(reference);
    }

    @Override
    public BonCarburant findByNumbonC(String numbonc) {
        return bonrepo.findByNumbonC(numbonc);
    }

    @Override
    public int save(BonCarburant boncarburant) {
        BonCarburant founded = bonrepo.findByNumbonC(boncarburant.getNumbonC());
        if (founded != null) {
            return -1;
        }
        BonCarburant.setNbr(boncarburant.getNbr() + 1);
        boncarburant.setFourniassooci(boncarburant.getFournisseurC().getDescriptionDropDown());
        boncarburant.setVehiculeassooci(boncarburant.getVehiculeC().getDescriptionDropDown());
        boncarburant.setReference(RandomStringUtils.random(6, true, false) + String.valueOf(boncarburant.getNbr()));
        bonrepo.save(boncarburant);
        return 1;
    }

    @Override
    public int update(BonCarburant boncarburant) {
      BonCarburant founded = bonrepo.findByReference(boncarburant.getReference());
      founded.setNumbonC(boncarburant.getNumbonC());
      founded.setDatebonC(boncarburant.getDatebonC());
      founded.setDescriptionC(boncarburant.getDescriptionC());
      founded.setFournisseurC(boncarburant.getFournisseurC());
      founded.setVehiculeC(boncarburant.getVehiculeC());
      founded.setPrixunitaireC(boncarburant.getPrixunitaireC());
      founded.setTotalbrutC(boncarburant.getTotalbrutC());
      founded.setMontantvignetteC(boncarburant.getMontantvignetteC());
      founded.setTypeC(boncarburant.getTypeC());
      founded.setQuantiteC(boncarburant.getQuantiteC());
      founded.setFourniassooci(boncarburant.getFournisseurC().getDescriptionDropDown());
      founded.setVehiculeassooci(boncarburant.getVehiculeC().getDescriptionDropDown());
      bonrepo.save(founded);
      return 1;
      
    }

    @Override
    public int delete(String reference) {
    BonCarburant founded = bonrepo.findByReference(reference);
    bonrepo.delete(founded);
    return 1;
    }

    @Override
    public List<BonCarburant> findAll() {
    return bonrepo.findAll();
    }
}
