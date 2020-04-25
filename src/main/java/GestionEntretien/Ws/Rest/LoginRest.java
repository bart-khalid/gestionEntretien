/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Ws.Rest;

import GestionEntretien.Bean.Login;
import GestionEntretien.Service.LoginService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Zakaria
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("GestionEntretien/Login")
public class LoginRest {
    @Autowired
    LoginService loginservice;

    @PostMapping("/")
    public int Save(@RequestBody Login login) {
        return loginservice.Save(login);
    }

    
    @GetMapping("/username/{username}")
    public Login findByUsername(@PathVariable String username) {
        return loginservice.findByUsername(username);
    }
    @GetMapping("/type/{type}")
    public List<Login> findByType(@PathVariable String type) {
        return loginservice.findByType(type);
    }
    
    @GetMapping("/Connect/username/{username}/password/{password}")
    public int Connect(@PathVariable String username,@PathVariable String password) {
        return loginservice.Connect(username, password);
    }
    
    
}
