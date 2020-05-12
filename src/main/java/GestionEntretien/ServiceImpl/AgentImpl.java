/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.Agent;
import GestionEntretien.Dao.AgentRepository;
import GestionEntretien.Service.AgentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class AgentImpl implements AgentService{ 

    @Autowired
    private AgentRepository agentRepository;
    
    
    
    
    @Override
    public int save(Agent agent) {
        Agent foundedAgent = agentRepository.findByCodeAgent(agent.getCodeAgent());
        
        if (foundedAgent != null){
            return -1;
        } else {
            agentRepository.save(agent);
             return 1;
        }
   
    }

    @Override
    public int update(Agent agent) {
        Agent foundedAgent = agentRepository.findByCodeAgent(agent.getCodeAgent());
        if (foundedAgent == null ){
            return -1;
        } else {
            foundedAgent.setAdresseDomicile(agent.getAdresseDomicile());
            foundedAgent.setDateEntree(agent.getDateEntree());
            foundedAgent.setNomAgent(agent.getNomAgent());
            foundedAgent.setTele(agent.getTele());
            
            agentRepository.save(foundedAgent);
            return 1;
        }
    }

    @Override
    public int deleteByCodeAgent(String codeAgent) {
        Agent foundedAgent = agentRepository.findByCodeAgent(codeAgent);
        agentRepository.delete(foundedAgent);
        return 1;
    }

    @Override
    public List<Agent> findAll() {
       return agentRepository.findAll();
    }
    
}
