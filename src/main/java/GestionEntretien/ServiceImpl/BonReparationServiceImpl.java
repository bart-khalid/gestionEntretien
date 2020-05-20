/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.BonReparation;
import GestionEntretien.Dao.BonReparationRepository;
import GestionEntretien.Service.BonReparationService;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Zakaria
 */
@Service
public class BonReparationServiceImpl implements BonReparationService {
    @Autowired
    private BonReparationRepository bonrepo;

    @Override
    public BonReparation findByReference(String reference) {
        return bonrepo.findByReference(reference);
    }

    @Override
    public BonReparation findByNumbonR(String numbonr) {
        return bonrepo.findByNumbonR(numbonr);
    }

    @Override
    public int save(BonReparation bonreparation) {
        BonReparation founded = bonrepo.findByNumbonR(bonreparation.getNumbonR());
        if (founded != null) {
            return -1;
        }
        BonReparation.setNbr(bonreparation.getNbr() + 1);
        bonreparation.setFourniassooci(bonreparation.getFournisseurR().getNomf()+" ,"+bonreparation.getFournisseurR().getAdressef());
        bonreparation.setVehiculeassooci(bonreparation.getVehiculeR().getMatricule()+" ,"+bonreparation.getVehiculeR().getMarque());
        bonreparation.setReference(RandomStringUtils.random(6, true, false) + String.valueOf(bonreparation.getNbr()));
        
        bonrepo.save(bonreparation);

        return 1;
    }

    @Override
    public int update(BonReparation bonreparation) {
      BonReparation founded = bonrepo.findByReference(bonreparation.getReference());
      founded.setFourniassooci(bonreparation.getFournisseurR().getNomf()+" ,"+bonreparation.getFournisseurR().getAdressef());
      founded.setVehiculeassooci(bonreparation.getVehiculeR().getMatricule()+" ,"+bonreparation.getVehiculeR().getMarque());
      founded.setNumbonR(bonreparation.getNumbonR());
      founded.setDatebonR(bonreparation.getDatebonR());
      founded.setDescriptionR(bonreparation.getDescriptionR());
      founded.setFournisseurR(bonreparation.getFournisseurR());
      founded.setVehiculeR(bonreparation.getVehiculeR());
      founded.setPrixunitaireR(bonreparation.getPrixunitaireR());
      founded.setTotalbrutR(bonreparation.getTotalbrutR());
      founded.setMontantvignetteR(bonreparation.getMontantvignetteR());
      founded.setQuantiteR(bonreparation.getQuantiteR());
      bonrepo.save(founded);
      return 1;
      
    }

    @Override
    public int delete(String reference) {
    BonReparation founded = bonrepo.findByReference(reference);
    bonrepo.delete(founded);
    return 1;
    }

    @Override
    public List<BonReparation> findAll() {
    return bonrepo.findAll();
    }
   

}
