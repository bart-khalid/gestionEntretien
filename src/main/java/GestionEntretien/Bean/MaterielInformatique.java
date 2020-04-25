/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author lenovo
 */
@Entity
public class MaterielInformatique extends Materiel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    

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
        if (!(object instanceof MaterielInformatique)) {
            return false;
        }
        MaterielInformatique other = (MaterielInformatique) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GestionEntretien.Bean.MaterielInformatique[ id=" + id + " ]";
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

    public List<LocalDetails> getLocalDetails() {
        return localDetails;
    }

    public void setLocalDetails(List<LocalDetails> localDetails) {
        this.localDetails = localDetails;
    }

    public List<EntretienMateriel> getEntretienMateriel() {
        return entretienMateriel;
    }

    public void setEntretienMateriel(List<EntretienMateriel> entretienMateriel) {
        this.entretienMateriel = entretienMateriel;
    }
    
}
