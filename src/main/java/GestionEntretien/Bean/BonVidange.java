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
public class BonVidange implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double numbonV;
    @JsonFormat(pattern = "yyyy-MM-dd")    
    private Date datebonV;
    private double montantvignetteV;
    private double prixunitaireV;
    private double totalbrutV;
    private double kilometrageV;
    private double quantiteV;
    private String descriptionV;
    private String typehuileV;
    private String reference;
    private static Long nbr=0000L;
    
    @ManyToOne
    private Vehicule vehiculeV;
    
    @ManyToOne
    private Fournisseur fournisseurV;

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
        BonVidange.nbr = nbr;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getNumbonV() {
        return numbonV;
    }

    public void setNumbonV(double numbonV) {
        this.numbonV = numbonV;
    }

    public Date getDatebonV() {
        return datebonV;
    }

    public void setDatebonV(Date datebonV) {
        this.datebonV = datebonV;
    }

    public double getMontantvignetteV() {
        return montantvignetteV;
    }

    public void setMontantvignetteV(double montantvignetteV) {
        this.montantvignetteV = montantvignetteV;
    }

    public double getPrixunitaireV() {
        return prixunitaireV;
    }

    public void setPrixunitaireV(double prixunitaireV) {
        this.prixunitaireV = prixunitaireV;
    }

    public double getTotalbrutV() {
        return totalbrutV;
    }

    public void setTotalbrutV(double totalbrutV) {
        this.totalbrutV = totalbrutV;
    }

    public double getKilometrageV() {
        return kilometrageV;
    }

    public void setKilometrageV(double kilometrageV) {
        this.kilometrageV = kilometrageV;
    }

    public double getQuantiteV() {
        return quantiteV;
    }

    public void setQuantiteV(double quantiteV) {
        this.quantiteV = quantiteV;
    }

    public String getDescriptionV() {
        return descriptionV;
    }

    public void setDescriptionV(String descriptionV) {
        this.descriptionV = descriptionV;
    }

    public String getTypehuileV() {
        return typehuileV;
    }

    public void setTypehuileV(String typehuileV) {
        this.typehuileV = typehuileV;
    }

    public Vehicule getVehiculeV() {
        return vehiculeV;
    }

    public void setVehiculeV(Vehicule vehiculeV) {
        this.vehiculeV = vehiculeV;
    }

    public Fournisseur getFournisseurV() {
        return fournisseurV;
    }

    public void setFournisseurV(Fournisseur fournisseurV) {
        this.fournisseurV = fournisseurV;
    }
    
    
}
