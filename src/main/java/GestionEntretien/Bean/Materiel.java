/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author lenovo
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Materiel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String marque;
    protected double nbrEntite;
    protected String nom;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateEntrerFac;
    
    
    @ManyToOne
    protected Locale localeItem;
    
    @OneToMany(mappedBy = "materielItem")
    protected List<Locale> localesIn;
    
    @OneToMany(mappedBy = "materiel")
    protected List<EntretienMateriel> entretienMateriel;

    public List<Locale> getLocales() {
        return localesIn;
    }

    public void setLocales(List<Locale> locales) {
        this.localesIn = locales;
    }

    public List<EntretienMateriel> getEntretienMateriel() {
        return entretienMateriel;
    }

    public void setEntretienMateriel(List<EntretienMateriel> entretienMateriel) {
        this.entretienMateriel = entretienMateriel;
    }
    
    

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public double getNbrEntite() {
        return nbrEntite;
    }

    public void setNbrEntite(double nbrEntite) {
        this.nbrEntite = nbrEntite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateEntrerFac() {
        return dateEntrerFac;
    }

    public void setDateEntrerFac(Date dateEntrerFac) {
        this.dateEntrerFac = dateEntrerFac;
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
        if (!(object instanceof Materiel)) {
            return false;
        }
        Materiel other = (Materiel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GestionEntretien.Bean.Materiel[ id=" + id + " ]";
    }
    
}
