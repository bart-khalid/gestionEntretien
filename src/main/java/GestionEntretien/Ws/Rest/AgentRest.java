/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Ws.Rest;

import GestionEntretien.Bean.Agent;
import GestionEntretien.Service.AgentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lenovo
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("GestionEntretien/agent")
public class AgentRest {

    @Autowired
    private AgentService agentService;

    @PostMapping("/")
    public int save(@RequestBody Agent agent) {
        return agentService.save(agent);
    }

    @PutMapping("/update")
    public int update(@RequestBody Agent agent) {
        return agentService.update(agent);
    }

    @DeleteMapping("/deleteAgent/{reference}")
    public int deleteByCodeAgent(@PathVariable String reference) {
        return agentService.deleteByCodeAgent(reference);
    }

    @GetMapping("/")
    public List<Agent> findAll() {
        return agentService.findAll();
    }

}
