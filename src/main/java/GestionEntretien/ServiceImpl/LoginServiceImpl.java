/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.Login;
import GestionEntretien.Service.LoginService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import GestionEntretien.Dao.LoginRepository;

/**
 *
 * @author Zakaria
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginRepository logindao;

    @Override
    public Login findByUsername(String username) {
        return logindao.findByUsername(username);
    }

    @Override
    public List<Login> findByType(String type) {
        return logindao.findByType(type);
    }

    @Override
    public int Connect(String username, String password) {
        Login foundedlogin = findByUsername(username);
        if (foundedlogin == null) {
            return -1;
        }
        if (foundedlogin.getPassword().equals(password) == false ) {
            return -2;
        } else {
            return 1;
        }
    }

    @Override
    public int Save(Login login) {
        Login foundedlogin = findByUsername(login.getUsername());
        if (foundedlogin != null) {
            return -1;
        }
        
        if (login.getUsername() == null || login.getUsername().equals("") ) {
            return -2;
        } else if (login.getPassword() == null || login.getPassword().equals("")) {
            return -3;
        } else if (login.getType() == null || login.getType().equals("")) {
            return -4;
        } else {
            logindao.save(login);
        }
        return 1;
    }

    @Override
    public List<Login> findAll() {
        return logindao.findAll();
    }

}
