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

/**
 *
 * @author lenovo
 */
@Entity
public class PrestationInterne extends Prestation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomAgent;
    private double codeAgent;

    public String getNomAgent() {
        return nomAgent;
    }

    public void setNomAgent(String nomAgent) {
        this.nomAgent = nomAgent;
    }

    public double getCodeAgent() {
        return codeAgent;
    }

    public void setCodeAgent(double codeAgent) {
        this.codeAgent = codeAgent;
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
        if (!(object instanceof PrestationInterne)) {
            return false;
        }
        PrestationInterne other = (PrestationInterne) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GestionEntretien.Bean.PrestationInterne[ id=" + id + " ]";
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

    public Date getDatePrestation() {
        return datePrestation;
    }

    public void setDatePrestation(Date datePrestation) {
        this.datePrestation = datePrestation;
    }

    public boolean isIsReclamed() {
        return isReclamed;
    }

    public void setIsReclamed(boolean isReclamed) {
        this.isReclamed = isReclamed;
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
