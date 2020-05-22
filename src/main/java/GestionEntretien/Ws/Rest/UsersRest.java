/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Ws.Rest;

import GestionEntretien.Bean.Users;
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
import GestionEntretien.Service.UsersService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author Zakaria
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("GestionEntretien/Login")
public class UsersRest {

    @Autowired
    UsersService usersservice;

    @PutMapping("/update")
    public int Update(@RequestBody Users login) {
        return usersservice.Update(login);
    }

    @PostMapping("/")
    public int Save(@RequestBody Users login) {
        return usersservice.Save(login);
    }

    @GetMapping("/username/{username}")
    public Users findByUsername(@PathVariable String username) {
        return usersservice.findByUsername(username);
    }

    @GetMapping("/type/{type}")
    public List<Users> findByType(@PathVariable String type) {
        return usersservice.findByType(type);
    }

    @GetMapping("/Connect/username/{username}/password/{password}")
    public int Connect(@PathVariable String username, @PathVariable String password) {
        return usersservice.Connect(username, password);
    }

    @GetMapping("/")
    public List<Users> findAll() {
        return usersservice.findAll();
    }

    @DeleteMapping("/delete/{reference}")
    public int Delete(@PathVariable String reference) {
        return usersservice.Delete(reference);
    }

}
