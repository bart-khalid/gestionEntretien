/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.Locale;
import GestionEntretien.Dao.LocaleRepository;
import GestionEntretien.Service.LocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        localeRepository.save(locale);
        return 1;
    }

    @Override
    public int update(Locale locale) {
        Locale foundedLocale = localeRepository.findByReference(locale.getReference());
        foundedLocale.setNom(locale.getNom());
        foundedLocale.setDepartement(locale.getDepartement());
        foundedLocale.setType(locale.getType());
        return 1;
    }

    @Override
    public int delete(String reference) {
        Locale foundedLocale = localeRepository.findByReference(reference);
        localeRepository.delete(foundedLocale);
        return 1;
    }
    
}
