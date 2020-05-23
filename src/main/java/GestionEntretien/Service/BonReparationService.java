/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Service;

import GestionEntretien.Bean.BonReparation;
import java.util.List;

/**
 *
 * @author Zakaria
 */
public interface BonReparationService {

    public BonReparation findByReference(String reference);

    public BonReparation findByNumbonR(String numbonr);

    public int save(BonReparation boncarburant);

    public int update(BonReparation boncarburant);

    public int delete(String reference);

    public List<BonReparation> findAll();
}
