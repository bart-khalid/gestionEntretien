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
public class PrestationInterne implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   
    private String reference;
    private String typeEntretien;
    private Date date;
    private boolean Reclamed;
    private String refrenceReclamation;
    private String nomAgent;
    private String nomLocale;

    @ManyToOne
    private Locale locale;

    @ManyToOne 
    private Agent agent;
    
    
    @ManyToOne
    private LocalDetails materielLoclae;

    
    
    public LocalDetails getMaterielLoclae() {
        return materielLoclae;
    }

    public void setMaterielLoclae(LocalDetails materielLoclae) {
        this.materielLoclae = materielLoclae;
    }

    
    
    
    public String getNomLocale() {
        return nomLocale;
    }

    public void setNomLocale(String nomLocale) {
        this.nomLocale = nomLocale;
    }

    
    
    
    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
    
    
    
    public boolean isReclamed() {
        return Reclamed;
    }

    public void setReclamed(boolean Reclamed) {
        this.Reclamed = Reclamed;
    }
    
    
    
    public String getNomAgent() {
        return nomAgent;
    }

    public void setNomAgent(String nomAgent) {
        this.nomAgent = nomAgent;
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
