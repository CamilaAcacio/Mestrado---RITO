/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Activity;
import model.Wasinformedby;
import dao.ActivityDAO;
import dao.TestingClassExecutionDAO;
import dao.TestingMethodExecutionDAO;
import dao.TestingSuiteExecutionDAO;
import model.Testingclassexecution;
import model.Testingmethodexecution;
import model.Testingsuiteexecution;

/**
 *
 * @author Camila
 */
class ReadWasInformedBy {

    public ReadWasInformedBy() {
    }
    
    public List<Wasinformedby> createWasInformedBy (String buildNumber) throws ClassNotFoundException, SQLException{
        List<Wasinformedby> listWasInformedBy = new ArrayList<>();
        
        List<Testingsuiteexecution> listTestingSuiteExecution = new ArrayList<>();
        listTestingSuiteExecution = TestingSuiteExecutionDAO.obterTestingSuiteexecutionPerId(buildNumber);
        
        List<Testingclassexecution> listTestingClassExe = new ArrayList<>();
        listTestingClassExe = TestingClassExecutionDAO.obterTestingClassExePerSuite(listTestingSuiteExecution.get(0).getIdtestingSuiteExecution());
        
        for(Testingclassexecution a: listTestingClassExe){
            Wasinformedby wasInformedBy = new Wasinformedby(listTestingSuiteExecution.get(0), a);
            listWasInformedBy.add(wasInformedBy);
            List<Testingmethodexecution> listTestingMethodExecution = new ArrayList<>();
            listTestingMethodExecution = TestingMethodExecutionDAO.obterTestingmethodexecutionPorClass(a.getIdtestingClassExecution());
            for(Testingmethodexecution b: listTestingMethodExecution){
                Wasinformedby wasInformedBy2 = new Wasinformedby(a, b);
                listWasInformedBy.add(wasInformedBy2);
                
            }
            
        }
     
        return listWasInformedBy;
    }
    
}
