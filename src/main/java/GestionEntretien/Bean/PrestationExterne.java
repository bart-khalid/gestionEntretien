/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author lenovo
 */
@Entity
public class PrestationExterne extends Prestation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomPrestataire;
    private double montantFac;
    private double numeroFac;
    private boolean isBonCommande;
    private boolean isBonLivraison;

    @OneToOne
    private PresBonCommande presBonCommande;

    @OneToOne
    private PresBonLivraison presBonLivraison;

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

    public double getNumeroFac() {
        return numeroFac;
    }

    public void setNumeroFac(double numeroFac) {
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

}
