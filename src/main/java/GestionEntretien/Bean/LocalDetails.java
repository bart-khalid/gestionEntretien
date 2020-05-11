/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Zakaria
 */
@Entity
public class LocalDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reference;
    private String materielLocale;
    private String localeAssocie;
    private Date dateAchat;

    
    @ManyToOne 
    private Locale locale;
    
    @ManyToOne
    private Materiel materiel;
    
    
    @OneToMany (mappedBy = "materiel")
    private List<Entretien> entretiensMateriele;
    
    
    @OneToMany(mappedBy = "materiel")
    private List<Reclamation> reclamations;

    
    
    
    
    
    public List<Entretien> getEntretiensMateriele() {
        return entretiensMateriele;
    }

    public void setEntretiensMateriele(List<Entretien> entretiensMateriele) {
        this.entretiensMateriele = entretiensMateriele;
    }

    
    
    
    
    
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
    
    
    public List<Reclamation> getReclamations() {
        return reclamations;
    }

    public void setReclamations(List<Reclamation> reclamations) {
        this.reclamations = reclamations;
    }
    

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

//    public Entretien getEntretiensMateriel() {
//        return entretiensMateriel;
//    }
//
//    public void setEntretiensMateriel(Entretien entretiensMateriel) {
//        this.entretiensMateriel = entretiensMateriel;
//    }
//    
    
    
    
    public String getMaterielLocale() {
        return materielLocale;
    }

    public void setMaterielLocale(String materielLocale) {
        this.materielLocale = materielLocale;
    }

    public String getLocaleAssocie() {
        return localeAssocie;
    }

    public void setLocaleAssocie(String localeAssocie) {
        this.localeAssocie = localeAssocie;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }
    

    public Materiel getMateriel() {
        return materiel;
    }

    public void setMateriel(Materiel materiel) {
        this.materiel = materiel;
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
        if (!(object instanceof LocalDetails)) {
            return false;
        }
        LocalDetails other = (LocalDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GestionEntretien.Bean.LocalDetails[ id=" + id + " ]";
    }

    public LocalDetails findByReeference(String reference) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
