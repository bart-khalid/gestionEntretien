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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author lenovo
 */
@Entity
public class PrestationExterne implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String referenceE;
    private String typeEntretienE;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateE;
    private boolean ReclamedE;
    
    private String nomPrestataireE;
    private double montantFacE;
    private String numeroFacE;
    private boolean bonCommandeE;
    private boolean bonLivraisonE;
    private String nomMateriel;
    private String nomLocale;

    private static Long nbrPresExterne = 0L;
    
    @ManyToOne
    private Locale locale;
    
    @ManyToOne
    private LocalDetails materielLocale;
    
    @OneToOne
    private PresBonCommande presBonCommandeE;
    
    @OneToOne
    private Reclamation reclamationE;

    @OneToOne
    private PresBonLivraison presBonLivraisonE;

  

    public Reclamation getReclamationE() {
        return reclamationE;
    }

    public void setReclamationE(Reclamation reclamationE) {
        this.reclamationE = reclamationE;
    }

    public String getNomMateriel() {
        return nomMateriel;
    }

    public void setNomMateriel(String nomMateriel) {
        this.nomMateriel = nomMateriel;
    }

    public String getNomLocale() {
        return nomLocale;
    }

    public void setNomLocale(String nomLocale) {
        this.nomLocale = nomLocale;
    }
    

    public static Long getNbrPresExterne() {
        return nbrPresExterne;
    }

    public static void setNbrPresExterne(Long nbrPresExterne) {
        PrestationExterne.nbrPresExterne = nbrPresExterne;
    }

    
    public boolean isReclamedE() {
        return ReclamedE;
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
        if (!(object instanceof PrestationExterne)) {
            return false;
        }
        PrestationExterne other = (PrestationExterne) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GestionEntretien.Bean.PrestationExterne[ id=" + id + " ]";
    }

    public PresBonCommande getPresBonCommandeE() {
        return presBonCommandeE;
    }

    public void setPresBonCommandeE(PresBonCommande presBonCommandeE) {
        this.presBonCommandeE = presBonCommandeE;
    }

    public PresBonLivraison getPresBonLivraisonE() {
        return presBonLivraisonE;
    }

    public void setPresBonLivraisonE(PresBonLivraison presBonLivraisonE) {
        this.presBonLivraisonE = presBonLivraisonE;
    }

   
    public String getReferenceE() {
        return referenceE;
    }

    public void setReferenceE(String referenceE) {
        this.referenceE = referenceE;
    }

    public String getTypeEntretienE() {
        return typeEntretienE;
    }

    public void setTypeEntretienE(String typeEntretienE) {
        this.typeEntretienE = typeEntretienE;
    }

    public Date getDateE() {
        return dateE;
    }

    public void setDateE(Date dateE) {
        this.dateE = dateE;
    }

   
    
    public LocalDetails getMaterielLocale() {
        return materielLocale;
    }

    public void setMaterielLocale(LocalDetails materielLocale) {
        this.materielLocale = materielLocale;
    }


    public void setReclamedE(boolean ReclamedE) {
        this.ReclamedE = ReclamedE;
    }

    public String getNomPrestataireE() {
        return nomPrestataireE;
    }

    public void setNomPrestataireE(String nomPrestataireE) {
        this.nomPrestataireE = nomPrestataireE;
    }

    public double getMontantFacE() {
        return montantFacE;
    }

    public void setMontantFacE(double montantFacE) {
        this.montantFacE = montantFacE;
    }

    public String getNumeroFacE() {
        return numeroFacE;
    }

    public void setNumeroFacE(String numeroFacE) {
        this.numeroFacE = numeroFacE;
    }

    public boolean isBonCommandeE() {
        return bonCommandeE;
    }

    public void setBonCommandeE(boolean bonCommandeE) {
        this.bonCommandeE = bonCommandeE;
    }

    public boolean isBonLivraisonE() {
        return bonLivraisonE;
    }

    public void setBonLivraisonE(boolean bonLivraisonE) {
        this.bonLivraisonE = bonLivraisonE;
    }

  

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

}
