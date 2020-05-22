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
public class Entretien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")    
    private Date dateEntretien;
    private String nomMateriel;
    private String nomLocale;
    private String prestataire;
    private double montant;
    private String numFacture;
    
    // locle detail c le materiel qui est specifier par un ref et un locale
    @ManyToOne
    private LocalDetails materiel;
    
    @ManyToOne
    private Locale loclale;

    
    
    public String getNomMateriel() {
        return nomMateriel;
    }

    public void setNomMateriel(String nomMateriel) {
        this.nomMateriel = nomMateriel;
    }

    public Entretien(Date dateEntretien, String nomMateriel, String prestataire, double montant, String numFacture) {
        this.dateEntretien = dateEntretien;
        this.nomMateriel = nomMateriel;
        this.prestataire = prestataire;
        this.montant = montant;
        this.numFacture = numFacture;
    }

    public Entretien() {
    }

    public String getNomLocale() {
        return nomLocale;
    }

    public void setNomLocale(String nomLocale) {
        this.nomLocale = nomLocale;
    }
    
    public LocalDetails getMateriel() {
        return materiel;
    }

    public void setMateriel(LocalDetails materiel) {
        this.materiel = materiel;
    }

    public Locale getLoclale() {
        return loclale;
    }

    public void setLoclale(Locale loclale) {
        this.loclale = loclale;
    }

    
    
    public Date getDateEntretien() {
        return dateEntretien;
    }

    public void setDateEntretien(Date dateEntretien) {
        this.dateEntretien = dateEntretien;
    }

    public String getPrestataire() {
        return prestataire;
    }

    public void setPrestataire(String prestataire) {
        this.prestataire = prestataire;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getNumFacture() {
        return numFacture;
    }

    public void setNumFacture(String numFacture) {
        this.numFacture = numFacture;
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
        if (!(object instanceof Entretien)) {
            return false;
        }
        Entretien other = (Entretien) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GestionEntretien.Bean.Entretien[ id=" + id + " ]";
    }
    
}
