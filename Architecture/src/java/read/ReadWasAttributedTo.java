/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import dao.AgentDAO;
import dao.BuildExecutionDAO;
import dao.EntityDAO;
import dao.EnvironmentDAO;
import dao.PersonDAO;
import dao.ProjectDAO;
import dao.TestingSuiteExecutionDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Agent;
import model.Buildexecution;
import model.Entityprov;
import model.Environment;
import model.Hasenvironment;
import model.Person;
import model.Project;
import model.Testingsuiteexecution;
import model.Wasattributedto;

/**
 *
 * @author Camila
 */
class ReadWasAttributedTo {

    public ReadWasAttributedTo() {
    }
    
    public Wasattributedto createWasAttributedTo (String buildNumber) throws ClassNotFoundException, SQLException{
        Wasattributedto wasAttributedTo = new Wasattributedto();
        
        List<Person> person = new ArrayList<>();
        person = PersonDAO.obterPersonPorId(buildNumber);
        
        List<Project> project = new ArrayList<>();
        project = ProjectDAO.obterTProjectPerId(buildNumber);
        
        wasAttributedTo = new Wasattributedto(person.get(0), project.get(0));
        
        return wasAttributedTo;
    }
    
}
