/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import dao.ActivityDAO;
import dao.BuildExecutionDAO;
import dao.EntityDAO;
import dao.SoftwareBuildDAO;
import dao.SoftwareExecutableDAO;
import dao.TestingLogDAO;
import dao.TestingSuiteExecutionDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Activity;
import model.Buildexecution;
import model.Entityprov;
import model.Softwarebuild;
import model.Softwareexecutable;
import model.Testinglog;
import model.Testingsuiteexecution;
import model.Wasgeneratedby;

/**
 *
 * @author Camila
 */
class ReadWasGeneratedBy {

    public ReadWasGeneratedBy() {
    }
    
    public List<Wasgeneratedby> createWasGeneratedBy (String buildNumber) throws ClassNotFoundException, SQLException{
        List<Wasgeneratedby> listWasGenereatedBy = new ArrayList<>();
        Wasgeneratedby wasgeneratedby = new Wasgeneratedby();
        
        List<Softwarebuild> softwarebuild = new ArrayList<>();
        softwarebuild = SoftwareBuildDAO.obterSoftwarebuildPorId(buildNumber);
        
        List<Buildexecution> buildExecution = new ArrayList<>();
        buildExecution = BuildExecutionDAO.obterBuildexecution(buildNumber);
        
        wasgeneratedby = new Wasgeneratedby(buildExecution.get(0), softwarebuild.get(0));
        listWasGenereatedBy.add(wasgeneratedby);
        
        List<Softwareexecutable> softwareExecutable = new ArrayList<>();
        softwareExecutable = SoftwareExecutableDAO.obterSoftwareexecutablePorId(buildNumber);
        
        wasgeneratedby = new Wasgeneratedby(buildExecution.get(0), softwareExecutable.get(0));
        listWasGenereatedBy.add(wasgeneratedby);
        
        List<Testinglog> testingLog = new ArrayList<>();
        testingLog = TestingLogDAO.obterTestinglogPorId(buildNumber);
        
        List<Testingsuiteexecution> testigSuiteExecution = new ArrayList<>();
        testigSuiteExecution = TestingSuiteExecutionDAO.obterTestingSuiteexecutionPerId(buildNumber);
        
        wasgeneratedby = new Wasgeneratedby(testingLog.get(0), testigSuiteExecution.get(0));
        listWasGenereatedBy.add(wasgeneratedby);
       
        return listWasGenereatedBy;
    }

    
}


