/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Bean;

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
public class Agent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String codeAgent;
    private String nomAgent;
    private Date dateEntree;
    private String entrepriseliee;
    private String adresseDomicile;
    private String tele;

    @OneToMany(mappedBy = "agent")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<PrestationInterne> prestations;

    
    public List<PrestationInterne> getPrestations() {
        return prestations;
    }

    public void setPrestations(List<PrestationInterne> prestations) {
        this.prestations = prestations;
    }
    
    
    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getCodeAgent() {
        return codeAgent;
    }

    public void setCodeAgent(String codeAgent) {
        this.codeAgent = codeAgent;
    }

    public String getNomAgent() {
        return nomAgent;
    }

    public void setNomAgent(String nomAgent) {
        this.nomAgent = nomAgent;
    }

    public Date getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(Date dateEntree) {
        this.dateEntree = dateEntree;
    }

    public String getEntrepriseliee() {
        return entrepriseliee;
    }

    public void setEntrepriseliee(String entrepriseliee) {
        this.entrepriseliee = entrepriseliee;
    }

    public String getAdresseDomicile() {
        return adresseDomicile;
    }

    public void setAdresseDomicile(String adresseDomicile) {
        this.adresseDomicile = adresseDomicile;
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
        if (!(object instanceof Agent)) {
            return false;
        }
        Agent other = (Agent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GestionEntretien.Bean.Agent[ id=" + id + " ]";
    }
    
}
