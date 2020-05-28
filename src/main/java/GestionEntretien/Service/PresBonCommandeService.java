/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Service;

import GestionEntretien.Bean.PresBonCommande;
import java.util.List;

/**
 *
 * @author lenovo
 */
public interface PresBonCommandeService {

    public List<PresBonCommande> findAll();

    public int delete(String reference);
}
