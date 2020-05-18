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
import javax.persistence.OneToMany;

/**
 *
 * @author lenovo
 */
@Entity
public class Fournisseur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomf;
    private String emailf;
    private String adressef;
    private String telephonef;

    

    @OneToMany(mappedBy = "fournisseurC")
    private List<BonCarburant> bonsCarburant;
    
    @OneToMany(mappedBy = "fournisseurR")
    private List<BonReparation> bonsReparation;
    
    @OneToMany(mappedBy = "fournisseurV")
    private List<BonVidange> bonsVidange;
    
    
    

    public List<BonCarburant> getBonsCarburant() {
        return bonsCarburant;
    }

    public void setBonsCarburant(List<BonCarburant> bonsCarburant) {
        this.bonsCarburant = bonsCarburant;
    }

    public List<BonReparation> getBonsReparation() {
        return bonsReparation;
    }

    public void setBonsReparation(List<BonReparation> bonsReparation) {
        this.bonsReparation = bonsReparation;
    }

    public List<BonVidange> getBonsVidange() {
        return bonsVidange;
    }

    public void setBonsVidange(List<BonVidange> bonsVidange) {
        this.bonsVidange = bonsVidange;
    }
    
    
    
    
    public String getNom() {
        return nomf;
    }

    public void setNom(String nomf) {
        this.nomf = nomf;
    }

    public String getEmail() {
        return emailf;
    }

    public void setEmail(String emailf) {
        this.emailf = emailf;
    }

    public String getAdresse() {
        return adressef;
    }

    public void setAdresse(String adressef) {
        this.adressef = adressef;
    }

    public String getTelephone() {
        return telephonef;
    }

    public void setTelephone(String telephonef) {
        this.telephonef = telephonef;
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
        if (!(object instanceof Fournisseur)) {
            return false;
        }
        Fournisseur other = (Fournisseur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GestionEntretien.Bean.Fournisseur[ id=" + id + " ]";
    }
    
}