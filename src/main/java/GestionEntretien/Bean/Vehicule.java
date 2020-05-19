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
public class Vehicule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reference;
    private String type;
    private String matricule;
    private String utilite;
    private String marque;
    private static Long nbr=0000L;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateEntrerParc;
    private String descriptionDropDown;


    @OneToMany(mappedBy = "vehiculeC")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<BonCarburant> bonsVehiculeC;

    @OneToMany(mappedBy = "vehiculeR")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<BonReparation> bonsVehiculeR;
    
    @OneToMany(mappedBy = "vehiculeV")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<BonVidange> bonsVehiculeV;

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
        Vehicule.nbr = nbr;
    }

    
    
    public List<BonCarburant> getBonsVehiculeC() {
        return bonsVehiculeC;
    }

    public void setBonsVehiculeC(List<BonCarburant> bonsVehiculeC) {
        this.bonsVehiculeC = bonsVehiculeC;
    }

    public List<BonReparation> getBonsVehiculeR() {
        return bonsVehiculeR;
    }

    public void setBonsVehiculeR(List<BonReparation> bonsVehiculeR) {
        this.bonsVehiculeR = bonsVehiculeR;
    }

    public List<BonVidange> getBonsVehiculeV() {
        return bonsVehiculeV;
    }

    public void setBonsVehiculeV(List<BonVidange> bonsVehiculeV) {
        this.bonsVehiculeV = bonsVehiculeV;
    }
    
    
    
    
    public Date getDateEntrerParc() {
        return dateEntrerParc;
    }

    public void setDateEntrerParc(Date dateEntrerParc) {
        this.dateEntrerParc = dateEntrerParc;
    }

  

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getUtilite() {
        return utilite;
    }

    public void setUtilite(String utilite) {
        this.utilite = utilite;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
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
        if (!(object instanceof Vehicule)) {
            return false;
        }
        Vehicule other = (Vehicule) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GestionEntretien.Bean.Vehicule[ id=" + id + " ]";
    }

}
