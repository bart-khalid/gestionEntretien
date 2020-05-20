/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Service;

import GestionEntretien.Bean.BonVidange;
import java.util.List;

/**
 *
 * @author Zakaria
 */
public interface BonVidangeService {
    public BonVidange findByReference(String reference);
    public BonVidange findByNumbonV(String numbonv);
    public int save(BonVidange boncarburant);
    public int update(BonVidange boncarburant);
    public int delete(String reference);
    public List<BonVidange> findAll();
}
