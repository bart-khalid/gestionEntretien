/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Ws.Rest;

import GestionEntretien.Bean.Locale;
import GestionEntretien.Service.LocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lenovo
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("GestionEntretien/locale")
public class LocaleRest {
    @Autowired
    private LocaleService localeService;
    
    @PostMapping("/")
    public int save(@RequestBody Locale locale){
        return localeService.save(locale);
    }
    
    @PutMapping("/update")
    public int update(@RequestBody Locale locale){
        return localeService.update(locale);
    }
    
    @DeleteMapping("/deleteLocale/{reference}")
    public int delete(@PathVariable String reference){
        return localeService.delete(reference);
    }
}
