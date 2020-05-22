/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Bean;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class FournisseurSV implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reference;
    private static Long nbr = 0000L;
    private String nomf;
    private String emailf;
    private String adressef;
    private String telephonef;
    private String descriptionDropDown;
    private String typef;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "fournisseurC")
    private List<BonCarburant> bonsCarburant;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "fournisseurR")
    private List<BonReparation> bonsReparation;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "fournisseurV")
    private List<BonVidange> bonsVidange;

    public String getTypef() {
        return typef;
    }

    public void setTypef(String typef) {
        this.typef = typef;
    }

    public String getDescriptionDropDown() {
        return descriptionDropDown;
    }

    public void setDescriptionDropDown(String descriptionDropDown) {
        this.descriptionDropDown = descriptionDropDown;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public static Long getNbr() {
        return nbr;
    }

    public static void setNbr(Long nbr) {
        FournisseurSV.nbr = nbr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomf() {
        return nomf;
    }

    public void setNomf(String nomf) {
        this.nomf = nomf;
    }

    public String getEmailf() {
        return emailf;
    }

    public void setEmailf(String emailf) {
        this.emailf = emailf;
    }

    public String getAdressef() {
        return adressef;
    }

    public void setAdressef(String adressef) {
        this.adressef = adressef;
    }

    public String getTelephonef() {
        return telephonef;
    }

    public void setTelephonef(String telephonef) {
        this.telephonef = telephonef;
    }

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FournisseurSV)) {
            return false;
        }
        FournisseurSV other = (FournisseurSV) object;
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
