/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Service;

import GestionEntretien.Bean.Vehicule;
import java.util.List;

/**
 *
 * @author lenovo
 */
public interface VehiculeService {

    public int save(Vehicule vehicule);

    public int update(Vehicule vehicule);

    public int delete(String matricule);

    public List<Vehicule> findAll();

    public Vehicule findByReference(String reference);

    public Vehicule findByMatricule(String Matricule);
}
