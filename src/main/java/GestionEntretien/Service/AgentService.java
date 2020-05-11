/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEntretien.Service;

import GestionEntretien.Bean.Agent;

/**
 *
 * @author lenovo
 */
public interface AgentService {
    public int save(Agent agent);
    public int update(Agent agent);
    public int deleteByCodeAgent(String codeAgent);
}
