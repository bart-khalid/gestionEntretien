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
public class PresBonCommande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reference;
    private double numeroBonCommande;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateBonCommande;
    private double montantC;
    private String nomPrestataireC;
    private String NomPrestationAssocie;

    @OneToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private PrestationExterne prestationExterne;

    public double getNumeroBonCommande() {
        return numeroBonCommande;
    }

    public void setNumeroBonCommande(double numeroBonCommande) {
        this.numeroBonCommande = numeroBonCommande;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    
    public String getNomPrestationAssocie() {
        return NomPrestationAssocie;
    }

    public void setNomPrestationAssocie(String NomPrestationAssocie) {
        this.NomPrestationAssocie = NomPrestationAssocie;
    }

    
    public Date getDateBonCommande() {
        return dateBonCommande;
    }

    public void setDateBonCommande(Date dateBonCommande) {
        this.dateBonCommande = dateBonCommande;
    }

    public double getMontantC() {
        return montantC;
    }

    public void setMontantC(double montantC) {
        this.montantC = montantC;
    }

    public String getNomPrestataireC() {
        return nomPrestataireC;
    }

    public void setNomPrestataireC(String nomPrestataireC) {
        this.nomPrestataireC = nomPrestataireC;
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
        if (!(object instanceof PresBonCommande)) {
            return false;
        }
        PresBonCommande other = (PresBonCommande) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GestionEntretien.Bean.PresBonCommande[ id=" + id + " ]";
    }

    public PrestationExterne getPrestationExterne() {
        return prestationExterne;
    }

    public void setPrestationExterne(PrestationExterne prestationExterne) {
        this.prestationExterne = prestationExterne;
    }

}
