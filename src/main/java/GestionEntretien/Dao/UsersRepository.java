/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Dao;

import GestionEntretien.Bean.Users;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Zakaria
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    public Users findByReference(String reference);
    public Users findByTelephone(String tele);
    public Users findByUsername(String username);
    public List<Users> findByType(String type);
    
}
