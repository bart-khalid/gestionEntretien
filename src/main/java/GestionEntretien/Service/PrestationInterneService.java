/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Service;

import GestionEntretien.Bean.PrestationInterne;
import java.util.List;

/**
 *
 * @author lenovo
 */
public interface PrestationInterneService {

    public int save(PrestationInterne preInterne);

    public int update(PrestationInterne preInterne);

    public int delete(String reference);

    public List<PrestationInterne> findAll();

}
