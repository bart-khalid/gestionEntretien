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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author lenovo
 */
@Entity
public class Materiel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reference;
    private String marque;
    private double nbrEntite;
    private String nom;
    private String type;
    //description
    private String descriptionDropDown;

    private static Long nbrMateriel = 0L;

    @OneToMany(mappedBy = "materiel")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    protected List<LocalDetails> localDetails;

    @ManyToOne
    private FournisseurSV fournisseur;

    public static Long getNbrMateriel() {
        return nbrMateriel;
    }

    public static void setNbrMateriel(Long nbrMateriel) {
        Materiel.nbrMateriel = nbrMateriel;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public FournisseurSV getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(FournisseurSV fournisseur) {
        this.fournisseur = fournisseur;
    }

    public List<LocalDetails> getLocalDetails() {
        return localDetails;
    }

    public void setLocalDetails(List<LocalDetails> localDetails) {
        this.localDetails = localDetails;
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
