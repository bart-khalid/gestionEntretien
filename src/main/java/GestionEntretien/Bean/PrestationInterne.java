/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author lenovo
 */
@Entity
public class PrestationInterne implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   
    private String referenceI;
    private String typeEntretienI;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateI;
    private boolean ReclamedI;
    private String refrenceReclamationI;
    private String nomAgentI;
    private String nomLocaleI;
    private String nomMaterielI;
    
    private static Long nbrPresInterne=0L;

    @ManyToOne
    private Locale locale;

    @ManyToOne 
    private Agent agent;
    
    
    @ManyToOne
    private LocalDetails materielLocale;
    
    @OneToOne
    private Reclamation reclamationI;

    public PrestationInterne() {
        this.ReclamedI = false;
    }

    public LocalDetails getMaterielLocale() {
        return materielLocale;
    }

    public void setMaterielLocale(LocalDetails materielLocale) {
        this.materielLocale = materielLocale;
    }

    


    public Reclamation getReclamationI() {
        return reclamationI;
    }

    public void setReclamationI(Reclamation reclamationI) {
        this.reclamationI = reclamationI;
    }



    
    public String getNomMaterielI() {
        return nomMaterielI;
    }

    public void setNomMaterielI(String nomMaterielI) {
        this.nomMaterielI = nomMaterielI;
    }

    public static Long getNbrPresInterne() {
        return nbrPresInterne;
    }

    public static void setNbrPresInterne(Long nbrPresInterne) {
        PrestationInterne.nbrPresInterne = nbrPresInterne;
    }

    
   
    
    
    
    
   
    
    
    
    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
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
        if (!(object instanceof PrestationInterne)) {
            return false;
        }
        PrestationInterne other = (PrestationInterne) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GestionEntretien.Bean.PrestationInterne[ id=" + id + " ]";
    }

    public String getReferenceI() {
        return referenceI;
    }

    public void setReferenceI(String referenceI) {
        this.referenceI = referenceI;
    }

    public String getTypeEntretienI() {
        return typeEntretienI;
    }

    public void setTypeEntretienI(String typeEntretienI) {
        this.typeEntretienI = typeEntretienI;
    }

    public Date getDateI() {
        return dateI;
    }

    public void setDateI(Date dateI) {
        this.dateI = dateI;
    }

    public boolean isReclamedI() {
        return ReclamedI;
    }

    public void setReclamedI(boolean ReclamedI) {
        this.ReclamedI = ReclamedI;
    }

    public String getRefrenceReclamationI() {
        return refrenceReclamationI;
    }

    public void setRefrenceReclamationI(String refrenceReclamationI) {
        this.refrenceReclamationI = refrenceReclamationI;
    }

    public String getNomAgentI() {
        return nomAgentI;
    }

    public void setNomAgentI(String nomAgentI) {
        this.nomAgentI = nomAgentI;
    }

    public String getNomLocaleI() {
        return nomLocaleI;
    }

    public void setNomLocaleI(String nomLocaleI) {
        this.nomLocaleI = nomLocaleI;
    }



    
    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

}
