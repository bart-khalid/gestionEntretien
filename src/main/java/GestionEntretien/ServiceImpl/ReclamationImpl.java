/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.LocalDetails;
import GestionEntretien.Bean.Locale;
import GestionEntretien.Bean.PrestationExterne;
import GestionEntretien.Bean.PrestationInterne;
import GestionEntretien.Bean.Reclamation;
import GestionEntretien.Bean.Users;
import GestionEntretien.Dao.LocalDetailsRepository;
import GestionEntretien.Dao.LocaleRepository;
import GestionEntretien.Dao.PrestationExterneRepository;
import GestionEntretien.Dao.PrestationInterneRepository;
import GestionEntretien.Dao.ReclamationRepository;
import GestionEntretien.Dao.UsersRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import GestionEntretien.Service.ReclamationService;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
// this import for random String generating
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author lenovo
 */
@Service
public class ReclamationImpl implements ReclamationService {

    @Autowired
    private ReclamationRepository reclamationRepository;

    @Autowired
    private UsersRepository loginRepository;

    @Autowired
    private LocalDetailsRepository localDetailsRepository;

    @Autowired
    private LocaleRepository localeRepository;

    @Autowired
    private PrestationInterneRepository pintenreRepository;

    @Autowired
    private PrestationExterneRepository pexterneRepository;

    @Override
    public List<Reclamation> findAll() {
        return reclamationRepository.findAll();
    }

    @Override
    public int save(Reclamation reclamation, String username) {
        Reclamation foundedReclamation = reclamationRepository.findByReference(reclamation.getReference());
        Users foundedReclamant = loginRepository.findByUsername(username);
        LocalDetails foundedMateriel = localDetailsRepository.findByReferenceML(reclamation.getMateriel().getReferenceML());

        if (foundedReclamation != null) {
            return -1;
        } else if (foundedReclamant == null) {
            return -2;

        } else if(reclamation.getLocale().getReference() == null) {
            return -3;
        } else {
            Reclamation.setNbr(Reclamation.getNbr() + 1);
            reclamation.setReference(RandomStringUtils.random(6, true, false) + String.valueOf(Reclamation.getNbr()));
            reclamation.setEtat("Pas Encore Vu");
            reclamation.setReclamentName(foundedReclamant.getNom() + ", " + foundedReclamant.getPrenom());
            reclamation.setDate(new Date());
            reclamation.setNomLocale(reclamation.getLocale().getDescriptionDropDown());
            reclamation.setNomMateriel("Pas de materiel");
            reclamation.setReclament(foundedReclamant);
            //update reclamations of reclamant
            List<Reclamation> reclamationsUser = foundedReclamant.getReclamations();
            reclamationsUser.add(reclamation);
            foundedReclamant.setReclamations(reclamationsUser);
            loginRepository.save(foundedReclamant);

            //update reclamations du locale
            Locale foundedLocale = localeRepository.findByReference(reclamation.getLocale().getReference());
            List<Reclamation> reclams = foundedLocale.getReclamations();
            reclams.add(reclamation);
            foundedLocale.setReclamations(reclams);
            localeRepository.save(foundedLocale);

            // update reclamations of materiel
            if (foundedMateriel != null) {
                reclamation.setMateriel(foundedMateriel);
                List<Reclamation> reclamationsMat = foundedMateriel.getReclamations();
                reclamationsMat.add(reclamation);
                foundedMateriel.setReclamations(reclamationsMat);
                localDetailsRepository.save(foundedMateriel);

                reclamation.setNomMateriel(reclamation.getMateriel().getDescriptionMaterielLocale());
            } else {
                reclamation.setMateriel(foundedMateriel);
            }

            reclamation.setDescreptionDropDownReclamation(reclamation.getReference() + ", " + reclamation.getObjet());
            reclamationRepository.save(reclamation);

            return 1;
        }

    }

    @Override
    public int update(Reclamation reclamation) {
        Reclamation foundedReclamation = reclamationRepository.findByReference(reclamation.getReference());
        LocalDetails loadedMateriel = reclamation.getMateriel();

        foundedReclamation.setObjet(reclamation.getObjet());
        foundedReclamation.setDescription(reclamation.getDescription());

        //update locale associe a cette reclamation
        if (!foundedReclamation.getLocale().getReference().equals(reclamation.getLocale().getReference())) {
            //delete cette reclam de la liste de lancian locale
            Locale foundedLocalee = localeRepository.findByReference(foundedReclamation.getLocale().getReference());
            List<Reclamation> reclamss = foundedLocalee.getReclamations();
            reclamss.remove(reclamation);
            foundedLocalee.setReclamations(reclamss);
            localeRepository.save(foundedLocalee);

            //update listes reclamations du nv materiel
            Locale foundedLocale = localeRepository.findByReference(reclamation.getLocale().getReference());
            foundedReclamation.setLocale(foundedLocale);
            List<Reclamation> reclams = foundedLocale.getReclamations();
            reclams.add(reclamation);
            foundedLocale.setReclamations(reclams);
            localeRepository.save(foundedLocale);
        }

        if (loadedMateriel != null) {
            if (!foundedReclamation.getMateriel().getReferenceML().equals(reclamation.getMateriel().getReferenceML())) {

                //update list reclamations ancien materiel
                LocalDetails LoadedMaterielOld = localDetailsRepository.findByReferenceML(foundedReclamation.getMateriel().getReferenceML());
                List<Reclamation> reclms = LoadedMaterielOld.getReclamations();
                reclms.remove(foundedReclamation);
                LoadedMaterielOld.setReclamations(reclms);
                localDetailsRepository.save(LoadedMaterielOld);

                //update liste reclamation du nv Materiel
                LocalDetails LoadedMaterielNv = localDetailsRepository.findByReferenceML(reclamation.getMateriel().getReferenceML());
                foundedReclamation.setMateriel(LoadedMaterielNv);
                List<Reclamation> recs = LoadedMaterielNv.getReclamations();
                recs.add(reclamation);
                LoadedMaterielNv.setReclamations(recs);
                localDetailsRepository.save(LoadedMaterielNv);
                reclamation.setNomMateriel(reclamation.getMateriel().getDescriptionMaterielLocale());
            }
        }

        reclamation.setDescreptionDropDownReclamation(reclamation.getReference() + ", " + reclamation.getObjet());
        reclamation.setNomLocale(reclamation.getLocale().getDescriptionDropDown());
        reclamationRepository.save(reclamation);
        return 1;
    }

    @Override
    public int delete(String refernce) {
        Reclamation founReclamation = reclamationRepository.findByReference(refernce);
        if (founReclamation == null) {
            return -1;
        } else {
            // set reclamation to null in prestations associate
            PrestationInterne prestationInterne = founReclamation.getPrestationInterne();
            PrestationExterne prestationExterne = founReclamation.getPrestationExterne();
            if(prestationExterne != null) {
                prestationExterne.setReclamationE(null);
            }
            if(prestationInterne != null) {
                prestationInterne.setReclamationI(null);
            }
            
            reclamationRepository.delete(founReclamation);
            return 1;
        }
    }

    @Override
    public int reclamationSeen(String reference) {
        Reclamation foundedReclamation = reclamationRepository.findByReference(reference);
        if (foundedReclamation == null) {
            return -1;

        } else {
            foundedReclamation.setEtat("Sous Traitement");
            reclamationRepository.save(foundedReclamation);
            return 1;
        }
    }

    @Override
    public List<Reclamation> findReclamationsNonTraiter() {
        List<Reclamation> reclamations = new ArrayList<>();
        List<Reclamation> recs = reclamationRepository.findAll();
        for (Reclamation rec : recs) {
            if (rec.getEtat().equals("Sous Traitement")){
                reclamations.add(rec);
            }
        }
        return reclamations;
    }

}
