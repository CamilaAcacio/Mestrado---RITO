/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import dao.ActedonbehalfofDAO;
import dao.ActivityDAO;
import dao.AgentDAO;
import dao.BuildExecutionDAO;
import dao.CommitDAO;
import dao.ComposedofDAO;
import dao.CoversDAO;
import dao.EntityDAO;
import dao.EnvironmentDAO;
import dao.HasenvironmentDAO;
import dao.PersonDAO;
import dao.ProjectDAO;
import dao.SoftwareArtifactDAO;
import dao.SoftwareBuildDAO;
import dao.SoftwareExecutableDAO;
import dao.SourceCodeDAO;
import dao.TestingClassDAO;
import dao.TestingClassExecutionDAO;
import dao.TestingLogDAO;
import dao.TestingMethodDAO;
import dao.TestingMethodExecutionDAO;
import dao.TestingSourceCodeDAO;
import dao.TestingSuiteDAO;
import dao.TestingSuiteExecutionDAO;
import dao.UsedDAO;
import dao.WasassociatedwithDAO;
import dao.WasattributedtoDAO;
import dao.WasderivedfromDAO;
import dao.WasgeneratedbyDAO;
import dao.WasinformedbyDAO;
import dao.TestingArtifactDAO;
import dao.TestingArtifactDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Actedonbehalfof;
import model.Composedof;
import model.Covers;
import model.Hasenvironment;
import model.Project;
import model.Softwareartifact;
import model.Sourcecode;
import model.StructCoversSourceCode;
import model.Testingartifact;
import model.Testingclass;
import model.Testingclassexecution;
import model.Testingmethod;
import model.Testingmethodexecution;
import model.Testingsourcecode;
import model.Used;
import model.Wasassociatedwith;
import model.Wasattributedto;
import model.Wasderivedfrom;
import model.Wasgeneratedby;
import model.Wasinformedby;

public class ReadAll {

    public ReadAll() {
    }

    public void readAll(String build, String log, String change, String environmentLog, String projectLog, List<String> listTestPath) throws SQLException, ClassNotFoundException {

        LerIdBuild idBuild = new LerIdBuild();
        String idBuildProject = idBuild.ReadIdBuild(build, projectLog);

        ReadAgent agent = new ReadAgent();
        AgentDAO.gravarAgent(agent.GetAgent(idBuildProject));
        
        ReadEntity entity = new ReadEntity();
        EntityDAO.gravarEntity(entity.GetEntity(idBuildProject));

        ReadActivity activity = new ReadActivity();
        ActivityDAO.gravarActivity(activity.GetActivity(idBuildProject));
        
        ReadPerson person = new ReadPerson();
        PersonDAO.gravarPerson(person.GetPerson(build, idBuildProject));
        
        ReadProject project = new ReadProject();
        Project pro = new Project();
        pro = project.GetProject(build, projectLog, idBuildProject);
        ProjectDAO.gravarProject(pro);
        
        ReadTestingArtifact testingArtifact = new ReadTestingArtifact();
        Testingartifact testing = new Testingartifact();
        testing = testingArtifact.getTestingArtifact(build, projectLog, idBuildProject);
        TestingArtifactDAO.gravarTestingartifact(testing);
        
        ReadSoftwareArtifact softwareArtifact = new ReadSoftwareArtifact();
        Softwareartifact software = new Softwareartifact();
        software = softwareArtifact.getSoftwareartifact(build, projectLog, idBuildProject);
        SoftwareArtifactDAO.gravarSoftwareartifact(software);
        
        ReadTestingLog testingLog = new ReadTestingLog();
        TestingLogDAO.gravarTestinglog(testingLog.GetTestingLog(build, log, projectLog, idBuildProject ));
        
        ReadSoftwareExecutable softwareExecutable = new ReadSoftwareExecutable();
        SoftwareExecutableDAO.gravarSoftwareexecutable(softwareExecutable.GetSoftwareExecutable(build, projectLog, idBuildProject));
        
        ReadSoftwareBuild softwareBuild = new ReadSoftwareBuild();
        SoftwareBuildDAO.gravarSoftwarebuild(softwareBuild.GetSoftwareBuild(build, projectLog, idBuildProject));
        
        ReadTestingEnvironment environment = new ReadTestingEnvironment();
        EnvironmentDAO.gravarEnvironment(environment.ReadTestingEnvironment(build, projectLog, environmentLog, idBuildProject));

        ReadBuildExecution buildexecution = new ReadBuildExecution();
        BuildExecutionDAO.gravarBuildexecution(buildexecution.getBuildExecution(build, idBuildProject));

        ReadCommit commit = new ReadCommit();
        CommitDAO.gravarCommitActivity(commit.GetCommit(build, change, idBuildProject));

        ReadTestingSuite testingSuite = new ReadTestingSuite();
        TestingSuiteDAO.gravarTestingsuite(testingSuite.GetTestingSuite(build, projectLog, idBuildProject));

        ReadSuiteExecution testingexecution = new ReadSuiteExecution();
        TestingSuiteExecutionDAO.gravarTestingsuiteexecution(testingexecution.GetTestingSuiteExecution(build, log, projectLog, idBuildProject));
        
        ReadTestingClass testingClass = new ReadTestingClass();
        List<Testingclass> listTestingclass = new ArrayList<>();
        listTestingclass = testingClass.GetTestingClass(build, log, projectLog, idBuildProject);
        for (int i = 0; i < listTestingclass.size(); i++) {
            TestingClassDAO.gravarTestingclass(listTestingclass.get(i));
        }
        
        ReadTestingClassExecution testingClassExecution = new ReadTestingClassExecution();
        List<Testingclassexecution> listTestingclassExecution = new ArrayList<>();
        listTestingclassExecution = testingClassExecution.GetReadTestingClass(build, log, projectLog, idBuildProject);
        for (int i = 0; i < listTestingclass.size(); i++) {
            TestingClassExecutionDAO.gravarTestingclassexecution(listTestingclassExecution.get(i));
        }
        
        ReadTestingSourceCode testingSourceCode = new ReadTestingSourceCode();
        List<Testingsourcecode> listTestingSourceCode = new ArrayList<>();
        listTestingSourceCode = testingSourceCode.GetTestingSourceCode(build, log, projectLog, idBuildProject);
        for (int i = 0; i < listTestingSourceCode.size(); i++) {
            TestingSourceCodeDAO.gravarTestingsourcecode(listTestingSourceCode.get(i));
        }
        
        ReadTestingMethod testingMethod = new ReadTestingMethod();
        List<Testingmethod> listTestingmethod = new ArrayList<>();
        listTestingmethod = testingMethod.GetTestingMethod(build, log, projectLog, idBuildProject);
        for (int i = 0; i < listTestingmethod.size(); i++) {
            TestingMethodDAO.gravarTestingmethod(listTestingmethod.get(i));
        }
        
        ReadTestingMethodExecution testingMethodExecution = new ReadTestingMethodExecution();
        List<Testingmethodexecution> listTestingMethodExecution = new ArrayList<>();
        listTestingMethodExecution = testingMethodExecution.GetTestingMethod(build, log, projectLog, idBuildProject);
        for (int j = 0; j < listTestingMethodExecution.size(); j++) {
            TestingMethodExecutionDAO.gravarTestingmethodexecution(listTestingMethodExecution.get(j));
        }
        
        ReadWasGeneratedBy readWasGeneratedBy = new ReadWasGeneratedBy();
        List<Wasgeneratedby> listWasGeneratedBy = new ArrayList<>();
        listWasGeneratedBy = readWasGeneratedBy.createWasGeneratedBy(idBuildProject);
        for (int j = 0; j < listWasGeneratedBy.size(); j++) {
           WasgeneratedbyDAO.gravarWasgeneratedby(listWasGeneratedBy.get(j));
        }
        
        ReadWasAssociatedWith readWasAssociated = new ReadWasAssociatedWith();
        List<Wasassociatedwith> listWasAssociatedWith = new ArrayList<>();
        listWasAssociatedWith = readWasAssociated.createWasAssociatedWith(idBuildProject);
        for (int j = 0; j < listWasAssociatedWith.size(); j++) {
           WasassociatedwithDAO.gravarWasassociatedwith(listWasAssociatedWith.get(j));
        }
        
        ReadEnvironment readHasEnvironment = new ReadEnvironment();
        List<Hasenvironment> hasEnvironment = new ArrayList<>();
        hasEnvironment = readHasEnvironment.createHasEnvironment(idBuildProject);
        for (int j = 0; j < hasEnvironment.size(); j++) {
           HasenvironmentDAO.gravarHasenvironment(hasEnvironment.get(j));
        }
        
        ReadUsed readUsed = new ReadUsed();
        List<Used> listUsed = new ArrayList<>();
        listUsed = readUsed.createUsed(idBuildProject);
        for (Used a: listUsed) {
           UsedDAO.gravarUsed(a);
        }
        
        ReadWasAttributedTo readWasAtributtedTo = new ReadWasAttributedTo();
        Wasattributedto wasAttributtedTo = new Wasattributedto();
        wasAttributtedTo = readWasAtributtedTo.createWasAttributedTo(idBuildProject);
        WasattributedtoDAO.gravarWasattributedto(wasAttributtedTo);
        
        ReadComposedOf readComposedOf = new ReadComposedOf();
        List<Composedof> composedOf = new ArrayList<>();
        composedOf = readComposedOf.createComposedOf(idBuildProject);
        for (Composedof a : composedOf) {
            ComposedofDAO.gravarComposedof(a);
        }
        
        ReadActedOnBehalfOf readActedOnBehalfOf = new ReadActedOnBehalfOf();
        Actedonbehalfof actedOnBehalfOf = new Actedonbehalfof();
        actedOnBehalfOf = readActedOnBehalfOf.createComposedOf(idBuildProject);
        ActedonbehalfofDAO.gravarActedonbehalfof(actedOnBehalfOf);
        
        ReadWasInformedBy readWasInformedBy = new ReadWasInformedBy();
        List<Wasinformedby> listWasInformedBy = new ArrayList<>();
        listWasInformedBy = readWasInformedBy.createWasInformedBy(idBuildProject);
        for (Wasinformedby a: listWasInformedBy) {
           WasinformedbyDAO.gravarWasinformedby(a);
        } 
        
        ReadWasDerivedFrom readWasDerivedFrom = new ReadWasDerivedFrom();
        List<Wasderivedfrom> wasDerivedFrom = new ArrayList<>();
        wasDerivedFrom = readWasDerivedFrom.createWasDerivedFrom(idBuildProject);
        for (int j = 0; j < wasDerivedFrom.size(); j++) {
            WasderivedfromDAO.gravarWasderivedfrom(wasDerivedFrom.get(j));
        }
        
        /*
        
        ReadSourceCode sourceCode = new ReadSourceCode();
        List<StructCoversSourceCode> listStructCoversSourceCode = new ArrayList<>();
        listStructCoversSourceCode = sourceCode.GetSourceCode(build, log, projectLog, listTestPath);
        for (int i = 0; i < listStructCoversSourceCode.size(); i++) {
            SourceCodeDAO.gravarSourcecode(listStructCoversSourceCode.get(i).getSourceCode());
        }

        ReadCover readCover = new ReadCover();
        List<Covers> listCovers = new ArrayList<>();
        listCovers = readCover.readCover(listStructCoversSourceCode);
        for (int i = 0; i < listCovers.size(); i++) {
            CoversDAO.gravarCovers(listCovers.get(i));
        }
        
        */

    }

}
