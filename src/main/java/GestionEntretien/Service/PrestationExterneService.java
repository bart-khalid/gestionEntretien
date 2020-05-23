/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Service;

import GestionEntretien.Bean.PrestationExterne;
import java.util.List;

/**
 *
 * @author lenovo
 */
public interface PrestationExterneService {
    public int save(PrestationExterne prestationExterne);
    public int update(PrestationExterne prestationExterne);
    public int delete(String reference);
    public List<PrestationExterne> findAll();
}
