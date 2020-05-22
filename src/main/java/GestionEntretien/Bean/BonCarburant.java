/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author lenovo
 */
@Entity
public class BonCarburant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reference;
    private static Long nbr = 0000L;
    private String numbonC;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date datebonC;
    private double montantvignetteC;
    private double prixunitaireC;
    private double totalbrutC;
    private double quantiteC;
    private String nomPrestataire;
    private String descriptionC;
    private String typeC;
    private String fourniassooci;
    private String vehiculeassooci;

    @ManyToOne
    private Vehicule vehiculeC;

    @ManyToOne
    private FournisseurSV fournisseurC;

    public String getFourniassooci() {
        return fourniassooci;
    }

    public void setFourniassooci(String fourniassooci) {
        this.fourniassooci = fourniassooci;
    }

    public String getVehiculeassooci() {
        return vehiculeassooci;
    }

    public void setVehiculeassooci(String vehiculeassooci) {
        this.vehiculeassooci = vehiculeassooci;
    }

    public double getQuantiteC() {
        return quantiteC;
    }

    public void setQuantiteC(double quantiteC) {
        this.quantiteC = quantiteC;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public static Long getNbr() {
        return nbr;
    }

    public static void setNbr(Long nbr) {
        BonCarburant.nbr = nbr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumbonC() {
        return numbonC;
    }

    public void setNumbonC(String numbonC) {
        this.numbonC = numbonC;
    }

    public Date getDatebonC() {
        return datebonC;
    }

    public void setDatebonC(Date datebonC) {
        this.datebonC = datebonC;
    }

    public double getMontantvignetteC() {
        return montantvignetteC;
    }

    public void setMontantvignetteC(double montantvignetteC) {
        this.montantvignetteC = montantvignetteC;
    }

    public double getPrixunitaireC() {
        return prixunitaireC;
    }

    public void setPrixunitaireC(double prixunitaireC) {
        this.prixunitaireC = prixunitaireC;
    }

    public double getTotalbrutC() {
        return totalbrutC;
    }

    public void setTotalbrutC(double totalbrutC) {
        this.totalbrutC = totalbrutC;
    }

    public String getNomPrestataire() {
        return nomPrestataire;
    }

    public void setNomPrestataire(String nomPrestataire) {
        this.nomPrestataire = nomPrestataire;
    }

    public String getDescriptionC() {
        return descriptionC;
    }

    public void setDescriptionC(String descriptionC) {
        this.descriptionC = descriptionC;
    }

    public String getTypeC() {
        return typeC;
    }

    public void setTypeC(String typeC) {
        this.typeC = typeC;
    }

    public Vehicule getVehiculeC() {
        return vehiculeC;
    }

    public void setVehiculeC(Vehicule vehiculeC) {
        this.vehiculeC = vehiculeC;
    }

    public FournisseurSV getFournisseurC() {
        return fournisseurC;
    }

    public void setFournisseurC(FournisseurSV fournisseurC) {
        this.fournisseurC = fournisseurC;
    }

}
