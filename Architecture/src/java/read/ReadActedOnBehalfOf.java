/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import dao.AgentDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Actedonbehalfof;
import model.Agent;

/**
 *
 * @author Camila
 */
class ReadActedOnBehalfOf {
    
     public Actedonbehalfof createComposedOf (String buildNumber) throws ClassNotFoundException, SQLException{
        Actedonbehalfof actedOnbehalfof = new Actedonbehalfof();
        
        List<Agent> agent = new ArrayList<>();
        agent = AgentDAO.obterAgent(buildNumber);
        
        List<Agent> agent2 = new ArrayList<>();
        agent2 = AgentDAO.obterAgent(buildNumber);
      
        
        actedOnbehalfof  = new Actedonbehalfof(agent.get(0), agent2.get(0));
        
        return actedOnbehalfof;
    }
    
}
