/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Dao;

import GestionEntretien.Bean.FournisseurSV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lenovo
 */
@Repository
public interface FournisseurSVRepository extends JpaRepository<FournisseurSV, Long> {

//    public FournisseurSV findByNomf(String nom);
//    @Query(value = "select fournisseur from fournisseur where nom=")
//    public Fournisseur findByNomAndAdresse(@Param String nom,@Param String adresse);
    public FournisseurSV findByNomfAndAdressef(String nomf, String adressef);
    public FournisseurSV findByTelephonef(String tele);
    public FournisseurSV findByEmailf(String mail);
    public FournisseurSV findByReference(String reference);
}
