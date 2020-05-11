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
public class Reclamation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reference;
    @JsonFormat(pattern = "yyyy-MM-dd -- HH:mm")
    private Date date;
    private String objet;
    private String description;
    private String nomLocale;
    private String nomMateriel;
    private String reclamentName;
    private String etat;
    static Long nbr=0000L;
    
    @ManyToOne 
    private LocalDetails materiel;
    
    @ManyToOne
    private Locale locale;
    
    @ManyToOne
    private Login reclament;
    
    
    
    

    public LocalDetails getMateriel() {
        return materiel;
    }

    public void setMateriel(LocalDetails materiel) {
        this.materiel = materiel;
    }
    
    
    
    

    public static Long getNbr() {
        return nbr;
    }

    public static void setNbr(Long nbr) {
        Reclamation.nbr = nbr;
    }

  
    
    
    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    
    public String getReclamentName() {
        return reclamentName;
    }

    public void setReclamentName(String reclamentName) {
        this.reclamentName = reclamentName;
    }

    
    public Login getReclament() {
        return reclament;
    }

    public void setReclament(Login reclament) {
        this.reclament = reclament;
    }

    
    
    public String getNomMateriel() {
        return nomMateriel;
    }

    public void setNomMateriel(String nomMateriel) {
        this.nomMateriel = nomMateriel;
    }
 

    public String getNomLocale() {
        return nomLocale;
    }

    public void setNomLocale(String nomLocale) {
        this.nomLocale = nomLocale;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
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
        if (!(object instanceof Reclamation)) {
            return false;
        }
        Reclamation other = (Reclamation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GestionEntretien.Bean.Reclamation[ id=" + id + " ]";
    }
    
}
