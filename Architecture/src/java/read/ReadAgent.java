/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import dao.AgentDAO;
import model.Agent;

/**
 *
 * @author Camila
 */
public class ReadAgent {

    public ReadAgent() {
    }

    public Agent GetAgent(String id) { // retorna agent
        Agent agent = new Agent(id);
        return agent;
    }

}
