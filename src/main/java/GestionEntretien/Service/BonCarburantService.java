/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Service;

import GestionEntretien.Bean.BonCarburant;
import java.util.List;

/**
 *
 * @author Zakaria
 */
public interface BonCarburantService {

    public BonCarburant findByReference(String reference);

    public BonCarburant findByNumbonC(String numbonc);

    public int save(BonCarburant boncarburant);

    public int update(BonCarburant boncarburant);

    public int delete(String reference);

    public List<BonCarburant> findAll();
}
