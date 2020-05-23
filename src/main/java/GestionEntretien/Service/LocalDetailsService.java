/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Service;

import GestionEntretien.Bean.LocalDetails;
import java.util.List;

/**
 *
 * @author lenovo
 */
public interface LocalDetailsService {

    public int save(LocalDetails localDetails);

    public int update(LocalDetails localDetails);

    public int delete(String referenceMaterielLocal);

    public List<LocalDetails> findAll();
}
