/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Service;

import GestionEntretien.Bean.Agent;
import GestionEntretien.Bean.Users;
import java.util.List;

/**
 *
 * @author Zakaria
 */
public interface UsersService {

    public Users findByTelephone(double tele);

    public Users findByUsername(String username);

    public List<Users> findByType(String type);

    public int Connect(String username, String password);

    public int Save(Users login);

    public int Update(Users login);

    public int Delete(String username);

    public Users findByReference(String reference);

    public List<Users> findAll();
    
  //  public int Updateuser(Users users);
    
}
