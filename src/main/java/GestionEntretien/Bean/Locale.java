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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author lenovo
 */
@Entity
public class Locale implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    
    @OneToMany(mappedBy = "localeItem")
    private List<Materiel> MyMateriels;
    
    @ManyToOne
    private Materiel materielItem;
    
    @OneToMany(mappedBy = "locale")
    private List<Prestation> prestation;

    public Materiel getMaterielItem() {
        return materielItem;
    }

    public void setMaterielItem(Materiel materielItem) {
        this.materielItem = materielItem;
    }
    
    
    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Materiel> getMyMateriels() {
        return MyMateriels;
    }

    public void setMyMateriels(List<Materiel> MyMateriels) {
        this.MyMateriels = MyMateriels;
    }

    public List<Prestation> getPrestation() {
        return prestation;
    }

    public void setPrestation(List<Prestation> prestation) {
        this.prestation = prestation;
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
        if (!(object instanceof Locale)) {
            return false;
        }
        Locale other = (Locale) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GestionEntretien.Bean.Locale[ id=" + id + " ]";
    }
    
}
