/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.ServiceImpl;

import GestionEntretien.Bean.Agent;
import GestionEntretien.Bean.Entretien;
import GestionEntretien.Bean.PrestationExterne;
import GestionEntretien.Bean.PrestationInterne;
import GestionEntretien.Dao.AgentRepository;
import GestionEntretien.Dao.EntretienRepository;
import GestionEntretien.Dao.PrestationExterneRepository;
import GestionEntretien.Dao.PrestationInterneRepository;
import GestionEntretien.Service.AgentService;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class AgentImpl implements AgentService {

    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private PrestationInterneRepository prestationInterneRepository;
    @Autowired
    private PrestationExterneRepository prestationExterneRepository;

    @Autowired
    private EntretienRepository entretienRepository;

    @Override
    public int save(Agent agent) {
        Agent foundedAgent = agentRepository.findByCodeAgent(agent.getCodeAgent());
        Agent foundeda = agentRepository.findByTel(agent.getTel());
        if (foundedAgent != null) {
            return -1;
        } else if (foundeda != null) {
            return -2;
        } else {
            agent.setDescriptionDropDown(agent.getCodeAgent() + ',' + agent.getNomAgent());
            Agent.setNbr(agent.getNbr() + 1);
            agent.setReference(RandomStringUtils.random(6, true, false) + String.valueOf(agent.getNbr()));
            agentRepository.save(agent);
            return 1;
        }

    }

    @Override
    public int update(Agent agent) {
        Agent foundedAgent = agentRepository.findByReference(agent.getReference());
        foundedAgent.setDescriptionDropDown(agent.getCodeAgent() + ',' + agent.getNomAgent());
        foundedAgent.setEntrepriseliee(agent.getEntrepriseliee());
        foundedAgent.setCodeAgent(agent.getCodeAgent());
        foundedAgent.setAdresseDomicile(agent.getAdresseDomicile());
        foundedAgent.setDateEntree(agent.getDateEntree());
        foundedAgent.setNomAgent(agent.getNomAgent());
        foundedAgent.setTel(agent.getTel());
        // update nomagent 
        List<PrestationInterne> foundedPresInternes = prestationInterneRepository.findAll();
        List<Entretien> listentretien = entretienRepository.findAll();
         for (PrestationInterne pres : foundedPresInternes) {
            if (pres.getAgent().getReference().equals(foundedAgent.getReference())) {
                pres.setNomAgentI(foundedAgent.getDescriptionDropDown());
            }
            
        for(Entretien ent : listentretien){
             if (pres.getTypeEntretienI().equals("materiel") && ent.getNumFacture().equals(pres.getReferenceI()) ) {
                ent.setPrestataire(foundedAgent.getDescriptionDropDown());
                entretienRepository.save(ent);
            }
        }
        
            prestationInterneRepository.save(pres);
        }
        
        
        agentRepository.save(foundedAgent);
        return 1;

    }

    @Override
    public int deleteByCodeAgent(String reference) {
        Agent foundedAgent = agentRepository.findByReference(reference);
        // set agent to null
        List<PrestationInterne> foundedPresInternes = prestationInterneRepository.findAll();
        foundedPresInternes.stream().filter((foundedPresInterne) -> (foundedPresInterne.getAgent().getReference().equals(foundedAgent.getReference()))).forEachOrdered((foundedPresInterne) -> {
            foundedPresInterne.setAgent(null);
        });
        // delete
        agentRepository.delete(foundedAgent);
        return 1;
    }

    @Override
    public List<Agent> findAll() {
        return agentRepository.findAll();
    }

    @Override
    public Agent findByCodeAgent(String codeAgent) {
        return agentRepository.findByCodeAgent(codeAgent);
    }

    @Override
    public Agent findByReference(String reference) {
        return agentRepository.findByReference(reference);
    }

    @Override
    public Agent findByTel(String tel) {
        return agentRepository.findByTel(tel);
    }

}
