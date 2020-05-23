/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Service;

import GestionEntretien.Bean.Materiel;
import java.util.List;

/**
 *
 * @author lenovo
 */
public interface MaterielService {

    public int save(Materiel materiel);

    public int update(Materiel materiel);

    public int deleteByReference(String reference);

    public List<Materiel> findAll();
}
