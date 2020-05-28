/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Service;

import GestionEntretien.Bean.PresBonLivraison;
import java.util.List;

/**
 *
 * @author lenovo
 */
public interface PresBonLivraisonService {

    public List<PresBonLivraison> findAll();

    public int delete(String reference);
}
