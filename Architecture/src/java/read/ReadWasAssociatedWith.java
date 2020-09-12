/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import dao.ActivityDAO;
import dao.AgentDAO;
import dao.BuildExecutionDAO;
import dao.CommitDAO;
import dao.PersonDAO;
import dao.TestingSuiteExecutionDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Activity;
import model.Agent;
import model.Buildexecution;
import model.Commitactivity;
import model.Person;
import model.Testingsuiteexecution;
import model.Wasassociatedwith;

/**
 *
 * @author Camila
 */
public class ReadWasAssociatedWith {

    public ReadWasAssociatedWith() {
    }
    
    public List<Wasassociatedwith> createWasAssociatedWith (String buildNumber) throws ClassNotFoundException, SQLException{
        List<Wasassociatedwith> listWasassociatedwith = new ArrayList<>();
        Wasassociatedwith wasassociatedwith = new Wasassociatedwith();
        
        List<Buildexecution> buildExecution = new ArrayList<>();
        buildExecution = BuildExecutionDAO.obterBuildexecution(buildNumber);
        
        List<Person> person = new ArrayList<>();
        person = PersonDAO.obterPersonPorId(buildNumber);
        
        wasassociatedwith = new Wasassociatedwith(buildExecution.get(0), person.get(0));
        listWasassociatedwith.add(wasassociatedwith);
        
        List<Commitactivity> commit = new ArrayList<>();
        commit = CommitDAO.obterCommitActivityPorId(buildNumber);
        
        wasassociatedwith  = new Wasassociatedwith(commit.get(0), person.get(0));
        listWasassociatedwith.add(wasassociatedwith);
        
        List<Testingsuiteexecution> testingSuite = new ArrayList<>();
        testingSuite = TestingSuiteExecutionDAO.obterTestingSuiteexecutionPerId(buildNumber);
        
        wasassociatedwith  = new Wasassociatedwith(testingSuite.get(0), person.get(0));
        listWasassociatedwith.add(wasassociatedwith);
        
        return listWasassociatedwith;
    }
    
}
