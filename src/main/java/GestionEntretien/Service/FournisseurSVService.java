/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Service;

import GestionEntretien.Bean.FournisseurSV;
import java.util.List;

/**
 *
 * @author lenovo
 */
public interface FournisseurSVService {

    public int save(FournisseurSV fournisseur);

    public int update(FournisseurSV fournisseur);

    public int delete(String reference);

    public List<FournisseurSV> findAll();

    public FournisseurSV findByReference(String reference);

}
