/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.Entretien;
import GestionEntretien.Bean.LocalDetails;
import GestionEntretien.Bean.Locale;
import GestionEntretien.Bean.Materiel;
import GestionEntretien.Bean.PrestationExterne;
import GestionEntretien.Bean.PrestationInterne;
import GestionEntretien.Bean.Reclamation;
import GestionEntretien.Dao.EntretienRepository;
import GestionEntretien.Dao.LocalDetailsRepository;
import GestionEntretien.Dao.LocaleRepository;
import GestionEntretien.Dao.MaterielRepository;
import GestionEntretien.Dao.PrestationExterneRepository;
import GestionEntretien.Dao.PrestationInterneRepository;
import GestionEntretien.Dao.ReclamationRepository;
import GestionEntretien.Service.LocalDetailsService;
import GestionEntretien.Service.LocaleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// this import for random String generating
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lenovo
 */
@Service
public class LocaleImpl implements LocaleService {

    @Autowired
    private LocaleRepository localeRepository;
    @Autowired
    private LocalDetailsRepository localDetailsRepository;
    @Autowired
    private MaterielRepository materielRepository;
    @Autowired
    private EntretienRepository entretienRepository;
    @Autowired
    private PrestationInterneRepository prestationInterneRepository;
    @Autowired
    private PrestationExterneRepository prestationExterneRepository;
    @Autowired
    private ReclamationRepository reclamationRepository;
   

    @Override
    public int save(Locale locale) {
        // generate random reference
        Locale.setNbrLocale(Locale.getNbrLocale() + 1);
        locale.setReference(RandomStringUtils.random(3, true, false) + String.valueOf(Locale.getNbrLocale()));
        Locale foundedLocale = localeRepository.findByReference(locale.getReference());
        while (foundedLocale != null) {
            locale.setReference(RandomStringUtils.random(3, true, false) + String.valueOf(Locale.getNbrLocale()));
            foundedLocale = localeRepository.findByReference(locale.getReference());
        }
        
        // 
        locale.setDescriptionDropDown(locale.getNomLocal() + " " + locale.getTypeLocal() + ", " + locale.getDepartement());

        localeRepository.save(locale);
        return 1;
    }

    @Override
    public int update(Locale locale) {
        Locale foundedLocale = localeRepository.findByReference(locale.getReference());
        foundedLocale.setNomLocal(locale.getNomLocal());
        foundedLocale.setDepartement(locale.getDepartement());
        foundedLocale.setTypeLocal(locale.getTypeLocal());
        foundedLocale.setDescriptionDropDown(locale.getNomLocal() + " " + locale.getTypeLocal() + ", " + locale.getDepartement());
         List<LocalDetails> mat = localDetailsRepository.findAll();
         for (LocalDetails loc : mat) {
            if (loc.getLocale().getReference().equals(foundedLocale.getReference())) {
                loc.setLocaleAssocie(foundedLocale.getDescriptionDropDown());
            }
            localDetailsRepository.save(loc);
        }
        //
         List<PrestationInterne> pres = prestationInterneRepository.findAll();
        for (PrestationInterne pre : pres) {
            if (pre.getLocale().getReference().equals(foundedLocale.getReference())) {
                pre.setNomLocaleI(foundedLocale.getDescriptionDropDown());
            }
            prestationInterneRepository.save(pre);
        }
        //ent.setNomLocale(foundedLocale.getDescriptionDropDown());
         List<Reclamation> rec = reclamationRepository.findAll();
           for (Reclamation recla : rec) {
            if (recla.getLocale().getReference().equals(foundedLocale.getReference())) {
                recla.setNomLocale(foundedLocale.getDescriptionDropDown());
            }
            reclamationRepository.save(recla);
           }
        //    .setNomLocale(foundedLocale.getDescriptionDropDown());
         List<Entretien> entre = entretienRepository.findAll();
          for (Entretien ent : entre) {
            if (ent.getLocale().getReference().equals(foundedLocale.getReference())) {
                ent.setNomLocale(foundedLocale.getDescriptionDropDown());
            }
            entretienRepository.save(ent);
           }
            
       
        localeRepository.save(foundedLocale);
        return 1;
    }

    @Override
    public int delete(String reference) {
        Locale foundedLocale = localeRepository.findByReference(reference);
        // set locale in reclamations to null
        List<Reclamation> reclamations = foundedLocale.getReclamations();
        reclamations.forEach((reclamation) -> {
            reclamation.setLocale(null);
            reclamation.setMateriel(null);
            reclamationRepository.save(reclamation); 
        });
       

        // set locale in prestations interne to null
        List<PrestationInterne> prestationsI = foundedLocale.getPrestationsI();
        prestationsI.forEach((prestation) -> {
            prestation.setLocale(null);
            prestationInterneRepository.save(prestation); 
        });

        // set locale in prestations externe to null
        List<PrestationExterne> prestationsE = foundedLocale.getPrestationsE();
        prestationsE.forEach((prestationExterne) -> {
            prestationExterne.setLocale(null);
            prestationExterneRepository.save(prestationExterne);
        });

        // set locale and materiel in enretiens to null
        List<Entretien> ents = foundedLocale.getEntretiens();
        ents.forEach((ent) -> {
            ent.setLocale(null);
            entretienRepository.save(ent);
        });

        List<LocalDetails> materiels = foundedLocale.getLocalDetails();
        for (LocalDetails materiel : materiels) {
            deleteMateriel(materiel.getReferenceML());
        }

        // delete the locale
        localeRepository.delete(foundedLocale);
        return 1;
    }

    @Override
    public List<Locale> findAll() {
        return localeRepository.findAll();
    }

    public void deleteMateriel(String reference) {
        LocalDetails foundedMaterielLocale = localDetailsRepository.findByReferenceML(reference);

        //Edit materiel asoocier
        if (foundedMaterielLocale.getMateriel() != null) {
            Materiel loadedMateriel = materielRepository.findByReference(foundedMaterielLocale.getMateriel().getReference());
            List<LocalDetails> mats = loadedMateriel.getLocalDetails();
            mats.remove(foundedMaterielLocale);
            loadedMateriel.setLocalDetails(mats);
            loadedMateriel.setNbrEntite(foundedMaterielLocale.getMateriel().getNbrEntite() - 1);
            materielRepository.save(loadedMateriel);
        }

        // list of prestations associate
        List<PrestationInterne> preIs = foundedMaterielLocale.getPrestationInternes();
        List<PrestationExterne> preEs = foundedMaterielLocale.getPrestationExternes();
        // set materiel to null
        preIs.forEach((pre) -> {
            pre.setMaterielLocale(null);
        });
        preEs.forEach((pre) -> {
            pre.setMaterielLocale(null);
        });

        // entretiens 
        List<Entretien> ents = foundedMaterielLocale.getEntretiensMateriele();
        ents.forEach((ent) -> {
            ent.setMateriel(null);
        });

        // reclamations   
        List<Reclamation> recs = foundedMaterielLocale.getReclamations();
        recs.forEach((rec) -> {
            rec.setLocale(null);
        });

        //delete this localeMateriels
        localDetailsRepository.delete(foundedMaterielLocale);
    }
}
