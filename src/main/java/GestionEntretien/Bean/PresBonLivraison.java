/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
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
public class PresBonLivraison implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reference;
    private String numeroBonLivraison;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateBonLivraison;
    private double montantL;
    private String nomPrestataireL;
    private String NomPrestationAssocie;

    @OneToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private PrestationExterne prestationExterneL;

    public String getNumeroBonLivraison() {
        return numeroBonLivraison;
    }

    public void setNumeroBonLivraison(String numeroBonLivraison) {
        this.numeroBonLivraison = numeroBonLivraison;
    }


    public Date getDateBonLivraison() {
        return dateBonLivraison;
    }

    public void setDateBonLivraison(Date dateBonLivraison) {
        this.dateBonLivraison = dateBonLivraison;
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
        if (!(object instanceof PresBonLivraison)) {
            return false;
        }
        PresBonLivraison other = (PresBonLivraison) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GestionEntretien.Bean.PresBonLivraison[ id=" + id + " ]";
    }

    public double getMontantL() {
        return montantL;
    }

    public void setMontantL(double montantL) {
        this.montantL = montantL;
    }

    public String getNomPrestataireL() {
        return nomPrestataireL;
    }

    public void setNomPrestataireL(String nomPrestataireL) {
        this.nomPrestataireL = nomPrestataireL;
    }

    public String getNomPrestationAssocie() {
        return NomPrestationAssocie;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    
    public void setNomPrestationAssocie(String NomPrestationAssocie) {
        this.NomPrestationAssocie = NomPrestationAssocie;
    }

    public PrestationExterne getPrestationExterneL() {
        return prestationExterneL;
    }

    public void setPrestationExterneL(PrestationExterne prestationExterneL) {
        this.prestationExterneL = prestationExterneL;
    }

   
}
