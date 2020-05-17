/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.Locale;
import GestionEntretien.Bean.Reclamation;
import GestionEntretien.Dao.LocaleRepository;
import GestionEntretien.Service.LocaleService;
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
public class LocaleImpl implements LocaleService{

    @Autowired
    private LocaleRepository localeRepository;
    
    @Override
    public int save(Locale locale) {
        Locale.setNbrLocale(Locale.getNbrLocale() + 1);
        locale.setReference(RandomStringUtils.random(3, true, false) + String.valueOf(Locale.getNbrLocale()));
        localeRepository.save(locale);
        return 1;
    }

    @Override
    public int update(Locale locale) {
        Locale foundedLocale = localeRepository.findByReference(locale.getReference());
        foundedLocale.setNomLocal(locale.getNomLocal());
        foundedLocale.setDepartement(locale.getDepartement());
        foundedLocale.setTypeLocal(locale.getTypeLocal());
        localeRepository.save(foundedLocale);
        return 1;
    }

    @Override
    public int delete(String reference) {
        Locale foundedLocale = localeRepository.findByReference(reference);
        localeRepository.delete(foundedLocale);
        return 1;
    }

    @Override
    public List<Locale> findAll() {
        return localeRepository.findAll();
    }
    
}
