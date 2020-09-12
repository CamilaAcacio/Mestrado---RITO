/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import dao.EntityDAO;
import dao.SoftwareArtifactDAO;
import dao.SoftwareBuildDAO;
import dao.SoftwareExecutableDAO;
import dao.SourceCodeDAO;
import dao.TestingLogDAO;
import dao.TestingSourceCodeDAO;
import dao.TestingSuiteDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Entityprov;
import model.Softwareartifact;
import model.Softwarebuild;
import model.Softwareexecutable;
import model.Sourcecode;
import model.Testinglog;
import model.Testingsourcecode;
import model.Testingsuite;
import model.Wasderivedfrom;

/**
 *
 * @author Camila
 */
class ReadWasDerivedFrom {

    public ReadWasDerivedFrom() {
    }
    
    public List<Wasderivedfrom> createWasDerivedFrom (String buildNumber) throws ClassNotFoundException, SQLException{
        List<Wasderivedfrom> listWasDerivedFrom = new ArrayList<>();
                
        List<Softwarebuild> listsoftwareBuild = new ArrayList<>();
        listsoftwareBuild = SoftwareBuildDAO.obterSoftwarebuildPorId(buildNumber);
        List<Softwareartifact> listSoftwareArtifact = new ArrayList<>();
        listSoftwareArtifact = SoftwareArtifactDAO.obterSoftwareartifactPerId(buildNumber);
        Wasderivedfrom wasDerivedfrom = new Wasderivedfrom(listsoftwareBuild.get(0), listSoftwareArtifact.get(0));
        listWasDerivedFrom.add(wasDerivedfrom);
        
        List<Softwareexecutable> listSoftwareExecutable = new ArrayList<>();
        listSoftwareExecutable = SoftwareExecutableDAO.obterSoftwareexecutablePorId(buildNumber);
        Wasderivedfrom wasDerivedfrom3 = new Wasderivedfrom(listSoftwareExecutable.get(0), listSoftwareArtifact.get(0));
        listWasDerivedFrom.add(wasDerivedfrom3);
        
        
        List<Testinglog> listTestingLog = new ArrayList<>();
        listTestingLog = TestingLogDAO.obterTestinglogPorId(buildNumber);
        List<Testingsuite> listTestingSuite = new ArrayList<>();
        listTestingSuite = TestingSuiteDAO.obterTestingSuitePerId(buildNumber);
        Wasderivedfrom wasDerivedfrom2 = new Wasderivedfrom(listTestingLog.get(0), listTestingSuite.get(0));
        listWasDerivedFrom.add(wasDerivedfrom2);
        
        List<Testingsourcecode> listTestingSourceCode = new ArrayList<>();
        listTestingSourceCode = TestingSourceCodeDAO.obterTestingsourcecodePorArtifact(buildNumber);
        List<Sourcecode> listSourceCode = new ArrayList<>();
        listSourceCode = SourceCodeDAO.obterSourcecodeName(buildNumber);
        //essa parte falta código, mais complexo do que está aqui.
        
        
        return listWasDerivedFrom;
    }
    
}
