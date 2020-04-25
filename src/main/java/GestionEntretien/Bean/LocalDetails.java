/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Bean;

import GestionEntretien.Bean.Locale;
import GestionEntretien.Bean.Materiel;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Zakaria
 */
@Entity
public class LocalDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    @ManyToOne
    private Materiel materiel;
    
    @ManyToOne
    private Locale local;
    
    

    public Materiel getMateriel() {
        return materiel;
    }

    public void setMateriel(Materiel materiel) {
        this.materiel = materiel;
    }

    public Locale getLocal() {
        return local;
    }

    public void setLocal(Locale local) {
        this.local = local;
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
        if (!(object instanceof LocalDetails)) {
            return false;
        }
        LocalDetails other = (LocalDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GestionEntretien.Bean.LocalDetails[ id=" + id + " ]";
    }
    
}
