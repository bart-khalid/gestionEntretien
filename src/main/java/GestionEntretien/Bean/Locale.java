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
public class Locale implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reference;
    private String nomLocal;
    private String departement;
    private String typeLocal;
    private double nbrMateriel;
    //desc
    private String descriptionDropDown;
    private static Long nbrLocale = 0L;

    @OneToMany(mappedBy = "locale")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<LocalDetails> localDetails;

    @OneToMany(mappedBy = "locale")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<PrestationInterne> prestationsI;

    @OneToMany(mappedBy = "locale")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<PrestationExterne> prestationsE;

    @OneToMany(mappedBy = "locale")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Reclamation> reclamations;
    
    @OneToMany(mappedBy = "locale")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Entretien> entretiens;
    
    
    public static Long getNbrLocale() {
        return nbrLocale;
    }

    public String getDescriptionDropDown() {
        return descriptionDropDown;
    }

    public List<Entretien> getEntretiens() {
        return entretiens;
    }

    public void setEntretiens(List<Entretien> entretiens) {
        this.entretiens = entretiens;
    }

    
    public void setDescriptionDropDown(String descriptionDropDown) {
        this.descriptionDropDown = descriptionDropDown;
    }

    public List<Reclamation> getReclamations() {
        return reclamations;
    }

    public void setReclamations(List<Reclamation> reclamations) {
        this.reclamations = reclamations;
    }

    public static void setNbrLocale(Long nbrLocale) {
        Locale.nbrLocale = nbrLocale;
    }

    public double getNbrMateriel() {
        return nbrMateriel;
    }

    public void setNbrMateriel(double nbrMateriel) {
        this.nbrMateriel = nbrMateriel;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getNomLocal() {
        return nomLocal;
    }

    public void setNomLocal(String nomLocal) {
        this.nomLocal = nomLocal;
    }

    public String getTypeLocal() {
        return typeLocal;
    }

    public void setTypeLocal(String typeLocal) {
        this.typeLocal = typeLocal;
    }

    public List<LocalDetails> getLocalDetails() {
        return localDetails;
    }

    public void setLocalDetails(List<LocalDetails> LocalDetails) {
        this.localDetails = LocalDetails;
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

    public List<PrestationInterne> getPrestationsI() {
        return prestationsI;
    }

    public void setPrestationsI(List<PrestationInterne> prestationsI) {
        this.prestationsI = prestationsI;
    }

    public List<PrestationExterne> getPrestationsE() {
        return prestationsE;
    }

    public void setPrestationsE(List<PrestationExterne> prestationsE) {
        this.prestationsE = prestationsE;
    }

}
