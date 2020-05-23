/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.Users;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import GestionEntretien.Dao.UsersRepository;
import GestionEntretien.Service.UsersService;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author Zakaria
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersdao;

    @Override
    public Users findByUsername(String username) {
        return usersdao.findByUsername(username);
    }

    @Override
    public List<Users> findByType(String type) {
        return usersdao.findByType(type);
    }

    @Override
    public int Connect(String username, String password) {
        Users foundedlogin = findByUsername(username);
        if (foundedlogin == null) {
            return -1;
        }
        if (foundedlogin.getPassword().equals(password) == false) {
            return -2;
        } else {
            return 1;
        }
    }

    @Override
    public int Save(Users users) {
        Users foundedlogin = findByUsername(users.getUsername());
        Users u = findByTelephone(users.getTelephone());
        if (foundedlogin != null) {
            return -1;
        }
        if (u != null) {
            return -5;
        }

        if (users.getUsername() == null || users.getUsername().equals("")) {
            return -2;
        } else if (users.getPassword() == null || users.getPassword().equals("")) {
            return -3;
        } else if (users.getType() == null || users.getType().equals("")) {
            return -4;
        } else {
            Users.setNbr(users.getNbr() + 1);
            users.setReference(RandomStringUtils.random(6, true, false) + String.valueOf(users.getNbr()));
            usersdao.save(users);
        }
        return 1;
    }

    @Override
    public List<Users> findAll() {
        return usersdao.findAll();
    }

    @Override
    public int Update(Users users) {
        Users user = usersdao.findByReference(users.getReference());
        user.setUsername(users.getUsername());
        user.setNom(users.getNom());
        user.setPassword(users.getPassword());
        user.setType(users.getType());
        user.setPrenom(users.getPrenom());
        user.setTelephone(users.getTelephone());
        usersdao.save(user);
        return 1;
    }

    @Override
    public int Delete(String reference) {
        Users user = usersdao.findByReference(reference);
        usersdao.delete(user);
        return 1;
    }

    @Override
    public Users findByReference(String reference) {
        return usersdao.findByReference(reference);
    }

    @Override
    public Users findByTelephone(String tele) {
        return usersdao.findByTelephone(tele);
    }

}
