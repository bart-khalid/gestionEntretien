/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Dao;

import GestionEntretien.Bean.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lenovo
 */
@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long>{
//    @Query(value = "select fournisseur from fournisseur where nom=")
//    public Fournisseur findByNomAndAdresse(String nom, String adresse);
}
