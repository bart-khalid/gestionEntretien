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
import javax.persistence.OneToOne;

/**
 *
 * @author lenovo
 */
@Entity
public class PrestationExterne implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String reference;
    private String typeEntretien;
    private Date date;
    private boolean Reclamed;
    private String refrenceReclamation;

    private String nomPrestataire;
    private double montantFac;
    private String numeroFac;
    private boolean bonCommande;
    private boolean bonLivraison;

    @ManyToOne
    protected Locale locale;

    @OneToOne
    private PresBonCommande presBonCommande;

    @OneToOne
    private PresBonLivraison presBonLivraison;

    public boolean isReclamed() {
        return Reclamed;
    }

    public void setReclamed(boolean Reclamed) {
        this.Reclamed = Reclamed;
    }

    public boolean isBonCommande() {
        return bonCommande;
    }

    public void setBonCommande(boolean BonCommande) {
        this.bonCommande = BonCommande;
    }

    public boolean isBonLivraison() {
        return bonLivraison;
    }

    public void setBonLivraison(boolean BonLivraison) {
        this.bonLivraison = BonLivraison;
    }

    public String getNomPrestataire() {
        return nomPrestataire;
    }

    public void setNomPrestataire(String nomPrestataire) {
        this.nomPrestataire = nomPrestataire;
    }

    public double getMontantFac() {
        return montantFac;
    }

    public void setMontantFac(double montantFac) {
        this.montantFac = montantFac;
    }

    public String getNumeroFac() {
        return numeroFac;
    }

    public void setNumeroFac(String numeroFac) {
        this.numeroFac = numeroFac;
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
        if (!(object instanceof PrestationExterne)) {
            return false;
        }
        PrestationExterne other = (PrestationExterne) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GestionEntretien.Bean.PrestationExterne[ id=" + id + " ]";
    }

    public PresBonCommande getPresBonCommande() {
        return presBonCommande;
    }

    public void setPresBonCommande(PresBonCommande presBonCommande) {
        this.presBonCommande = presBonCommande;
    }

    public PresBonLivraison getPresBonLivraison() {
        return presBonLivraison;
    }

    public void setPresBonLivraison(PresBonLivraison presBonLivraison) {
        this.presBonLivraison = presBonLivraison;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getTypeEntretien() {
        return typeEntretien;
    }

    public void setTypeEntretien(String typeEntretien) {
        this.typeEntretien = typeEntretien;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRefrenceReclamation() {
        return refrenceReclamation;
    }

    public void setRefrenceReclamation(String refrenceReclamation) {
        this.refrenceReclamation = refrenceReclamation;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

}
