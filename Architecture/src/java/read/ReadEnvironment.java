/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import dao.BuildExecutionDAO;
import dao.EnvironmentDAO;
import dao.TestingSuiteExecutionDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Buildexecution;
import model.Environment;
import model.Hasenvironment;
import model.Testingsuiteexecution;

/**
 *
 * @author Camila
 */
public class ReadEnvironment {

    public ReadEnvironment() {
    }
    
     public List<Hasenvironment> createHasEnvironment (String buildNumber) throws ClassNotFoundException, SQLException{
        Hasenvironment hasEnvironment = new Hasenvironment();
        List<Hasenvironment> listHasenvironments = new ArrayList<>();
        
        List<Buildexecution> buildExecution = new ArrayList<>();
        buildExecution = BuildExecutionDAO.obterBuildexecution(buildNumber);
        
        List<Environment> environment = new ArrayList<>();
        environment = EnvironmentDAO.obterEnvironment(buildNumber);
       
        List<Testingsuiteexecution> testingSuiteExecution = new ArrayList<>();
        testingSuiteExecution = TestingSuiteExecutionDAO.obterTestingSuiteexecutionPerId(buildNumber);
        
        hasEnvironment  = new Hasenvironment(buildExecution.get(0), environment.get(0));
        listHasenvironments.add(hasEnvironment);
        
        hasEnvironment  = new Hasenvironment(environment.get(0), testingSuiteExecution.get(0));
        listHasenvironments.add(hasEnvironment);
        
        return listHasenvironments;
    }
    
}
