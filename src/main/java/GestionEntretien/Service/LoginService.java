/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Service;

import GestionEntretien.Bean.Login;
import java.util.List;

/**
 *
 * @author Zakaria
 */
public interface LoginService {
    
     public Login findByUsername(String username);
     public List<Login> findByType(String type);
     public int Connect(String username,String password);
     public int Save(Login login );
}
