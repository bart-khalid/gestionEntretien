/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.Entretien;
import GestionEntretien.Bean.PrestationInterne;
import GestionEntretien.Bean.Reclamation;
import GestionEntretien.Dao.EntretienRepository;
import GestionEntretien.Dao.PrestationInterneRepository;
import GestionEntretien.Dao.ReclamationRepository;
import GestionEntretien.Service.PrestationInterneService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class PrestationInterneImpl implements PrestationInterneService {

    @Autowired
    private PrestationInterneRepository prestationInterneRepository;

    @Autowired
    private ReclamationRepository reclamationRepository;

    @Autowired
    private EntretienRepository entretienRepository;

    @Override
    public int save(PrestationInterne preInterne) {

        preInterne.setNomAgent(preInterne.getAgent().getNomAgent() + ", " + preInterne.getAgent().getCodeAgent());
        //si reclamer alors regler letat de la reclamation associe
        if (preInterne.isReclamed()) {
            Reclamation foundedRclamation = reclamationRepository.findByReference(preInterne.getRefrenceReclamation());
            foundedRclamation.setEtat("Bien Traitée");
            reclamationRepository.save(foundedRclamation);
        }
        //si pres d un materiel donc il sagit d un entretien
        if (preInterne.getMaterielLoclae() != null) {
            Entretien ent = new Entretien(preInterne.getDate(), preInterne.getMaterielLoclae().getMaterielLocale(), preInterne.getNomAgent(), 0, preInterne.getReference());
            entretienRepository.save(ent);
        }
        prestationInterneRepository.save(preInterne);
        return 1;
    }

    @Override
    public int update(PrestationInterne preInterne) {
        PrestationInterne foundedPrestationInterne = prestationInterneRepository.findByReference(preInterne.getReference());

        //update attriibutes de bases
        foundedPrestationInterne.setTypeEntretien(preInterne.getTypeEntretien());
        foundedPrestationInterne.setDate(preInterne.getDate());
        foundedPrestationInterne.setLocale(preInterne.getLocale());

        //si on change l'agent
        if (foundedPrestationInterne.getAgent().getCodeAgent() != preInterne.getAgent().getCodeAgent()) {
            foundedPrestationInterne.setAgent(preInterne.getAgent());
            foundedPrestationInterne.setNomAgent(preInterne.getAgent().getNomAgent() + ", " + preInterne.getAgent().getCodeAgent());
        }
        //si c un entretien Mat et on a changer mat
        if (preInterne.getMaterielLoclae() != null) {
            // ajout d un materiel pour cette pres
            if (foundedPrestationInterne.getMaterielLoclae() == null) {
                foundedPrestationInterne.setMaterielLoclae(preInterne.getMaterielLoclae());
                Entretien ent = new Entretien(preInterne.getDate(), preInterne.getMaterielLoclae().getMaterielLocale(), preInterne.getNomAgent(), 0, preInterne.getReference());
                entretienRepository.save(ent);
            } else {
                //suppression de l ancien entretien du materiel
                Entretien foundedEntretienOld = entretienRepository.findByNumFacture(foundedPrestationInterne.getReference());
                entretienRepository.delete(foundedEntretienOld);
                // creation un nouveau entretien de ce materiel
                foundedPrestationInterne.setMaterielLoclae(preInterne.getMaterielLoclae());
                Entretien ent = new Entretien(preInterne.getDate(), preInterne.getMaterielLoclae().getMaterielLocale(), preInterne.getNomAgent(), 0, preInterne.getReference());
                entretienRepository.save(ent);
            }
        }
        //update etat reclamation associer
        if (!foundedPrestationInterne.getRefrenceReclamation().equals(preInterne.getRefrenceReclamation())) {
            Reclamation foundedReclamationold = reclamationRepository.findByReference(foundedPrestationInterne.getRefrenceReclamation());
            Reclamation foundedReclamationNew = reclamationRepository.findByReference(preInterne.getRefrenceReclamation());
            if (foundedPrestationInterne.isReclamed()) {
                foundedReclamationold.setEtat("Sous Traitement");
                foundedReclamationNew.setEtat("Bien Traitée");
                reclamationRepository.save(foundedReclamationNew);
                reclamationRepository.save(foundedReclamationold);
            }

        }

        prestationInterneRepository.save(preInterne);
        return 1;
    }

    @Override
    public int delete(String reference) {
        PrestationInterne foundedPreInterne = prestationInterneRepository.findByReference(reference);
        prestationInterneRepository.delete(foundedPreInterne);
        return 1;
    }

    @Override
    public List<PrestationInterne> findAll() {
        return prestationInterneRepository.findAll();
    }

}
