/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.Entretien;
import GestionEntretien.Bean.FournisseurSV;
import GestionEntretien.Bean.LocalDetails;
import GestionEntretien.Bean.Materiel;
import GestionEntretien.Bean.PrestationInterne;
import GestionEntretien.Bean.Reclamation;
import GestionEntretien.Dao.EntretienRepository;
import GestionEntretien.Dao.LocalDetailsRepository;
import GestionEntretien.Dao.MaterielRepository;
import GestionEntretien.Dao.PrestationInterneRepository;
import GestionEntretien.Dao.ReclamationRepository;
import GestionEntretien.Service.MaterielService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// this import for random String generating
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author lenovo
 */
@Service
public class MaterielImpl implements MaterielService {

    @Autowired
    private MaterielRepository materielRepository;
    @Autowired
    private LocalDetailsRepository localDetailsRepository;
    @Autowired
    private EntretienRepository entretienRepository;
    @Autowired
    private PrestationInterneRepository prestationInterneRepository;
    @Autowired
    private ReclamationRepository reclamationRepository;

    @Override
    public int save(Materiel materiel) {
        // generate a random reference 
        Materiel.setNbrMateriel(Materiel.getNbrMateriel() + 1);
        materiel.setReference(RandomStringUtils.random(6, true, false) + String.valueOf(Materiel.getNbrMateriel()));
        Materiel foundedMateriel = materielRepository.findByReference(materiel.getReference());
        while(foundedMateriel != null) {
            materiel.setReference(RandomStringUtils.random(6, true, false) + String.valueOf(Materiel.getNbrMateriel()));
            foundedMateriel = materielRepository.findByReference(materiel.getReference());
        }
        materiel.setMarque(materiel.getFournisseur().getNomf());
        materiel.setDescriptionDropDown(materiel.getNom() + ", " + materiel.getMarque());
        materielRepository.save(materiel);
        return 1;
    }

    @Override
    public int update(Materiel materiel) {
        Materiel foundedMateriel = materielRepository.findByReference(materiel.getReference());

        foundedMateriel.setFournisseur(materiel.getFournisseur());
        foundedMateriel.setMarque(materiel.getFournisseur().getNomf());
        foundedMateriel.setNom(materiel.getNom());
        foundedMateriel.setType(materiel.getType());
        foundedMateriel.setDescriptionDropDown(materiel.getNom() + ", " + materiel.getMarque());
        List<LocalDetails> materielLocales = localDetailsRepository.findAll();
        for(LocalDetails mate : materielLocales) {
            if(mate.getMateriel().getReference().equals(foundedMateriel.getReference())){
                mate.setMaterielLocale(foundedMateriel.getDescriptionDropDown());
                mate.setDescriptionMaterielLocale(mate.getReferenceML() + ", " + mate.getMaterielLocale());
            if(mate.getPrestationInternes() != null){
            List<PrestationInterne> pres = prestationInterneRepository.findAll();
                for (PrestationInterne pre : pres) {
                    if(pre.getMaterielLocale().getReferenceML().equals(mate.getReferenceML())){
                    pre.setNomMaterielI(mate.getReferenceML() + ", " + foundedMateriel.getDescriptionDropDown());
                    prestationInterneRepository.save(pre);
                    }
                }
            }
            //
            if(mate.getReclamations()!= null ){
            List<Reclamation> recl = reclamationRepository.findAll();
             for (Reclamation rec : recl) {
                    if(rec.getMateriel().getReferenceML().equals(mate.getReferenceML())){
                    rec.setNomMateriel(mate.getReferenceML() + ", " + foundedMateriel.getDescriptionDropDown());
                    reclamationRepository.save(rec);
                    }
                }
            }
            //setNomMateriel(localDetails.getReferenceML() + ", " + localDetails.getMaterielLocale());
            if(mate.getLocale().getEntretiens() != null){
            List<Entretien> entre = entretienRepository.findAll();
           for (Entretien ent : entre) {
                    if(ent.getMateriel().getReferenceML().equals(mate.getReferenceML())){
                    ent.setNomMateriel(mate.getReferenceML() + ", " + foundedMateriel.getDescriptionDropDown());
                    entretienRepository.save(ent);
                    }
                }
           }
            
            }
            localDetailsRepository.save(mate);
        }
        materielRepository.save(foundedMateriel);
        return 1;
    }

    @Override
    public int deleteByReference(String reference) {
        Materiel foundedMateriel = materielRepository.findByReference(reference);
        List<LocalDetails> materielLocales = foundedMateriel.getLocalDetails();
        materielLocales.forEach((m) -> {
            m.setMateriel(null);
        });
        materielRepository.delete(foundedMateriel);
        return 1;
    }

    @Override
    public List<Materiel> findAll() {
        return materielRepository.findAll();
    }

}
