/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Bean;

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
    private double numeroBon;
    private Date dateBon;
    private double montant;
    private double numFacture;
    private String nomPrestataire;
    
    @ManyToOne
    private Vehicule vehicule;
    
    private String Description;

    @ManyToOne
    private FournisseurSV fournisseur;

    
    
    
    
    
    public FournisseurSV getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(FournisseurSV fournisseur) {
        this.fournisseur = fournisseur;
    }
    
    
    
    
    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BonReparation)) {
            return false;
        }
        BonReparation other = (BonReparation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GestionEntretien.Bean.Reparation[ id=" + id + " ]";
    }

    public double getNumeroBon() {
        return numeroBon;
    }

    public void setNumeroBon(double numeroBon) {
        this.numeroBon = numeroBon;
    }

    public Date getDateBon() {
        return dateBon;
    }

    public void setDateBon(Date dateBon) {
        this.dateBon = dateBon;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getNumFacture() {
        return numFacture;
    }

    public void setNumFacture(double numFacture) {
        this.numFacture = numFacture;
    }

    public String getNomPrestataire() {
        return nomPrestataire;
    }

    public void setNomPrestataire(String nomPrestataire) {
        this.nomPrestataire = nomPrestataire;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

}
