/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import dao.ActivityDAO;
import dao.BuildExecutionDAO;
import dao.EntityDAO;
import dao.SoftwareArtifactDAO;
import dao.TestingClassDAO;
import dao.TestingClassExecutionDAO;
import dao.TestingMethodDAO;
import dao.TestingMethodExecutionDAO;
import dao.TestingSuiteDAO;
import dao.TestingSuiteExecutionDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Activity;
import model.Buildexecution;
import model.Entityprov;
import model.Softwareartifact;
import model.Testingclass;
import model.Testingclassexecution;
import model.Testingmethod;
import model.Testingmethodexecution;
import model.Testingsuite;
import model.Testingsuiteexecution;
import model.Used;

/**
 *
 * @author Camila
 */
class ReadUsed {

    public ReadUsed() {
    }
    
    
    public List<Used> createUsed (String buildNumber) throws ClassNotFoundException, SQLException{
        List<Used> listUsed = new ArrayList<>();
        Used used = new Used();
        
        List<Buildexecution> buildExecution = new ArrayList<>();
        buildExecution = BuildExecutionDAO.obterBuildexecution(buildNumber);
        List<Softwareartifact> softwareArtifact = new ArrayList<>();
        softwareArtifact = SoftwareArtifactDAO.obterSoftwareartifactPerId(buildNumber);
        used = new Used(buildExecution.get(0), softwareArtifact.get(0));
        listUsed.add(used);
        
        List<Testingsuiteexecution> testingSuiteExecution = new ArrayList<>();
        testingSuiteExecution = TestingSuiteExecutionDAO.obterTestingSuiteexecutionPerId(buildNumber);
        List<Testingsuite> testingSuite = new ArrayList<>();
        testingSuite = TestingSuiteDAO.obterTestingSuitePerId(buildNumber);
        used = new Used(testingSuite.get(0), testingSuiteExecution.get(0));
        listUsed.add(used);
        
        List<Testingclassexecution> listTestingClassExecution = new ArrayList<>();
        listTestingClassExecution = TestingClassExecutionDAO.obterTestingClassExePerProject(buildNumber);
        for(Testingclassexecution a: listTestingClassExecution){
            List<Testingclass> testingClass = new ArrayList<>();
            testingClass = TestingClassDAO.obterTestingclassPerID(a.getTestingclass().getIdtestingClass());
            used = new Used(a, testingClass.get(0));
            listUsed.add(used);
            List<Testingmethodexecution> listTestingMethodExecution = new ArrayList<>();
            listTestingMethodExecution = TestingMethodExecutionDAO.obterTestingmethodexecutionPorClass(a.getIdtestingClassExecution());
            for(Testingmethodexecution b : listTestingMethodExecution){
                List<Testingmethod> testingMethod = new ArrayList<>();
                testingMethod = TestingMethodDAO.obterTestingmethodPorId(b.getTestingmethod().getIdtestingMethod());
                used = new Used(testingMethod.get(0), b);
                listUsed.add(used);
            } 
        }
        
       return listUsed;
    }

    
}
