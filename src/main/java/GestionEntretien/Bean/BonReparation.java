/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
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
public class BonReparation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;    
    private String numbonR;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date datebonR;
    private String reference;
    private static Long nbr=0000L;
    private double montantvignetteR;
    private double prixunitaireR;
    private double totalbrutR;
    private double quantiteR;
    private String descriptionR;
    private String fourniassooci;
    private String vehiculeassooci;
    
    @ManyToOne
    private Vehicule vehiculeR;
    
    @ManyToOne
    private FournisseurSV fournisseurR;

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
        BonReparation.nbr = nbr;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumbonR() {
        return numbonR;
    }

    public void setNumbonR(String numbonR) {
        this.numbonR = numbonR;
    }

    public Date getDatebonR() {
        return datebonR;
    }

    public void setDatebonR(Date datebonR) {
        this.datebonR = datebonR;
    }

    public double getMontantvignetteR() {
        return montantvignetteR;
    }

    public void setMontantvignetteR(double montantvignetteR) {
        this.montantvignetteR = montantvignetteR;
    }

    public double getPrixunitaireR() {
        return prixunitaireR;
    }

    public void setPrixunitaireR(double prixunitaireR) {
        this.prixunitaireR = prixunitaireR;
    }

    public double getTotalbrutR() {
        return totalbrutR;
    }

    public void setTotalbrutR(double totalbrutR) {
        this.totalbrutR = totalbrutR;
    }

    public double getQuantiteR() {
        return quantiteR;
    }

    public void setQuantiteR(double quantiteR) {
        this.quantiteR = quantiteR;
    }

    public String getDescriptionR() {
        return descriptionR;
    }

    public void setDescriptionR(String descriptionR) {
        this.descriptionR = descriptionR;
    }

    public Vehicule getVehiculeR() {
        return vehiculeR;
    }

    public void setVehiculeR(Vehicule vehiculeR) {
        this.vehiculeR = vehiculeR;
    }

    public FournisseurSV getFournisseurR() {
        return fournisseurR;
    }

    public void setFournisseurR(FournisseurSV fournisseurR) {
        this.fournisseurR = fournisseurR;
    }

    
}
