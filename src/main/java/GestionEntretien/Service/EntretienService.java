/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Service;

import GestionEntretien.Bean.Entretien;
import java.util.List;

/**
 *
 * @author Zakaria
 */
public interface EntretienService {
  public  List<Entretien> findAll();
  public int delete(String reference);
  public Entretien findByNumFacture(String reference);

}
