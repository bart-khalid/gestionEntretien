/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Service;

import GestionEntretien.Bean.Locale;
import java.util.List;

/**
 *
 * @author lenovo
 */
public interface LocaleService {

    public int save(Locale locale);

    public int update(Locale locale);

    public int delete(String reference);

    public List<Locale> findAll();
}
