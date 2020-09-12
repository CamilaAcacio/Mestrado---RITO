/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontology;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import dao.ActedonbehalfofDAO;
import dao.ActivityDAO;
import dao.AgentDAO;
import dao.BuildExecutionDAO;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import model.Project;
import dao.ProjectDAO;
import model.Entityprov;
import dao.EntityDAO;
import java.sql.SQLException;
import model.Activity;
import model.Agent;
import model.Buildexecution;
import model.Commitactivity;
import dao.CommitDAO;
import dao.ComposedofDAO;
import dao.CoversDAO;
import dao.EnvironmentDAO;
import dao.HasenvironmentDAO;
import dao.PersonDAO;
import dao.SoftwareArtifactDAO;
import dao.SoftwareBuildDAO;
import dao.SoftwareExecutableDAO;
import dao.SourceCodeDAO;
import dao.TestingArtifactDAO;
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
import model.Actedonbehalfof;
import model.Composedof;
import model.Covers;
import model.Environment;
import model.Hasenvironment;
import model.Person;
import model.Softwareartifact;
import model.Softwarebuild;
import model.Softwareexecutable;
import model.Sourcecode;
import model.Testingartifact;
import model.Testingclass;
import model.Testingclassexecution;
import model.Testinglog;
import model.Testingmethod;
import model.Testingmethodexecution;
import model.Testingsourcecode;
import model.Testingsuite;
import model.Testingsuiteexecution;
import model.Used;
import model.Wasassociatedwith;
import model.Wasattributedto;
import model.Wasderivedfrom;
import model.Wasgeneratedby;
import model.Wasinformedby;

public class DataHandler {

    private final OntologyController controller;

    public DataHandler() throws SQLException, ClassNotFoundException {
        controller = OntologyController.getInstance();
    }

    /**
     * Classe para a inclusão de Triplas RDF na ontologia. Permite a inclusão de
     * novos indivíduos, propriedades ou relacionamentos.
     *
     * @param subject
     * @param predicate
     * @param object
     * @return
     */
    public boolean addTriple(String subject, String predicate, String object) {
        Resource sbj = controller.getInfModel().getResource(subject);
        if (sbj == null) {
            sbj = controller.getInfModel().createResource(subject);
        }

        Property prop = controller.getInfModel().getProperty(predicate);
        if (prop == null) {
            prop = ResourceFactory.createProperty(predicate);
        }

        Resource obj = controller.getInfModel().getResource(object);
        if (obj != null) {
            sbj.addProperty(prop, obj);
            return true;
        }
        return false;
    }

    public static void loadDAO() throws SQLException, ClassNotFoundException {

        Model model = ModelFactory.createDefaultModel();

        model.read(OntologyController.ONTOLOGY);

        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, model);
        ontModel.prepare();

        //Prepara as objectsProperties
        ObjectProperty composedOf = ontModel.getObjectProperty(OntologyController.URI + "composedOf");
        ObjectProperty covers = ontModel.getObjectProperty(OntologyController.URI + "covers");
        ObjectProperty hasEnvironment = ontModel.getObjectProperty(OntologyController.URI + "hasEnvironment");
        ObjectProperty hasSourceCode = ontModel.getObjectProperty(OntologyController.URI + "hasSourceCoude");
        ObjectProperty wasInfluencedBy = ontModel.getObjectProperty(OntologyController.URI + "wasInfluencedBy");
        ObjectProperty actedOnBehalfOf = ontModel.getObjectProperty(OntologyController.URI + "actedOnBehalfOf");
        ObjectProperty used = ontModel.getObjectProperty(OntologyController.URI + "used");
        ObjectProperty wasAssociateWith = ontModel.getObjectProperty(OntologyController.URI + "wasAssociatedWith");
        ObjectProperty wasAttributedTo = ontModel.getObjectProperty(OntologyController.URI + "wasAttributedTo");
        ObjectProperty wasDerivedFrom = ontModel.getObjectProperty(OntologyController.URI + "wasDerivedFrom");
        ObjectProperty wasGeneratedBy = ontModel.getObjectProperty(OntologyController.URI + "wasGeneratedBy");
        ObjectProperty wasInformedBy = ontModel.getObjectProperty(OntologyController.URI + "wasInformedBy");

        //Prepara as dataProperties
        DatatypeProperty entityId = ontModel.getDatatypeProperty(OntologyController.URI + "entityId");
        DatatypeProperty entityName = ontModel.getDatatypeProperty(OntologyController.URI + "entityName");
        DatatypeProperty agentId = ontModel.getDatatypeProperty(OntologyController.URI + "agentId");
        DatatypeProperty activityId = ontModel.getDatatypeProperty(OntologyController.URI + "activityId");
        DatatypeProperty buildExeDuration = ontModel.createDatatypeProperty(OntologyController.URI + "buildExeDuration");
        DatatypeProperty buildExeId = ontModel.getDatatypeProperty(OntologyController.URI + "buildExeId");
        DatatypeProperty buildExeResult = ontModel.getDatatypeProperty(OntologyController.URI + "buildExeResult");
        DatatypeProperty buildExeWorkspace = ontModel.getDatatypeProperty(OntologyController.URI + "buildExeWorkspace");
        DatatypeProperty commit = ontModel.getDatatypeProperty(OntologyController.URI + "commit");
        DatatypeProperty commitAuthor = ontModel.getDatatypeProperty(OntologyController.URI + "commitAuthor");
        DatatypeProperty commitDate = ontModel.getDatatypeProperty(OntologyController.URI + "commitDate");
        DatatypeProperty commiter = ontModel.getDatatypeProperty(OntologyController.URI + "commiter");
        DatatypeProperty commitHour = ontModel.getDatatypeProperty(OntologyController.URI + "commitHour");
        DatatypeProperty commitId = ontModel.getDatatypeProperty(OntologyController.URI + "commitId");
        DatatypeProperty commitParent = ontModel.getDatatypeProperty(OntologyController.URI + "commitParent");
        DatatypeProperty commitTree = ontModel.getDatatypeProperty(OntologyController.URI + "commitTree");
        DatatypeProperty environmentId = ontModel.getDatatypeProperty(OntologyController.URI + "environmentId");
        DatatypeProperty environmentJavaClassVersion = ontModel.getDatatypeProperty(OntologyController.URI + "environmentJavaClassVersion");
        DatatypeProperty environmentJavaRuntimeClass = ontModel.getDatatypeProperty(OntologyController.URI + "environmentJavaRuntimeClass");
        DatatypeProperty environmentJavaRuntimeVersion = ontModel.getDatatypeProperty(OntologyController.URI + "environmentJavaRuntimeVersion");
        DatatypeProperty environmentJavaSpecificationName = ontModel.getDatatypeProperty(OntologyController.URI + "environmentJavaSpecificationName");
        DatatypeProperty environmentJavaSpecificationVersion = ontModel.getDatatypeProperty(OntologyController.URI + "environmentJavaVmSpecificationVersion");
        DatatypeProperty environmentOsName = ontModel.getDatatypeProperty(OntologyController.URI + "environmentOsName");
        DatatypeProperty environmentOsVersion = ontModel.getDatatypeProperty(OntologyController.URI + "environmentOsVersion");
        DatatypeProperty environmentVmVersion = ontModel.getDatatypeProperty(OntologyController.URI + "environmentVmVersion");
        DatatypeProperty personId = ontModel.getDatatypeProperty(OntologyController.URI + "personId");
        DatatypeProperty personName = ontModel.getDatatypeProperty(OntologyController.URI + "personName");
        DatatypeProperty projectId = ontModel.getDatatypeProperty(OntologyController.URI + "projectId");
        DatatypeProperty projectName = ontModel.getDatatypeProperty(OntologyController.URI + "projectName");
        DatatypeProperty projectVersion = ontModel.getDatatypeProperty(OntologyController.URI + "projectVersion");
        DatatypeProperty softwareBuildDuration = ontModel.getDatatypeProperty(OntologyController.URI + "softwareBuildDuration");
        DatatypeProperty softwareBuildId = ontModel.getDatatypeProperty(OntologyController.URI + "softwareBuildId");
        DatatypeProperty softwareExecutableId = ontModel.getDatatypeProperty(OntologyController.URI + "softwareExecutableId");
        DatatypeProperty softwareExecutableName = ontModel.getDatatypeProperty(OntologyController.URI + "softwareExecutableName");
        DatatypeProperty sourceCodeId = ontModel.getDatatypeProperty(OntologyController.URI + "sourceCodeId");
        DatatypeProperty sourceCodeName = ontModel.getDatatypeProperty(OntologyController.URI + "sourceCodeName");
        DatatypeProperty sourceCodePath = ontModel.getDatatypeProperty(OntologyController.URI + "sourceCodePath");
        DatatypeProperty startedAtTime = ontModel.getDatatypeProperty(OntologyController.URI + "startedAtTime");
        DatatypeProperty testingClassExeExecutionTime = ontModel.getDatatypeProperty(OntologyController.URI + "testingClassExeExecutionTime");
        DatatypeProperty testingClassExeId = ontModel.getDatatypeProperty(OntologyController.URI + "testingClassExeId");
        DatatypeProperty testingClassId = ontModel.getDatatypeProperty(OntologyController.URI + "testingClassId");
        DatatypeProperty testingClassName = ontModel.getDatatypeProperty(OntologyController.URI + "testingClassName");
        DatatypeProperty testingLogGenerationDate = ontModel.getDatatypeProperty(OntologyController.URI + "testingLogGenerationDate");
        DatatypeProperty testingLogId = ontModel.getDatatypeProperty(OntologyController.URI + "testingLogId");
        DatatypeProperty testingLogPath = ontModel.getDatatypeProperty(OntologyController.URI + "testingLogPath");
        DatatypeProperty testingSCId = ontModel.getDatatypeProperty(OntologyController.URI + "testingSCId");
        DatatypeProperty testingSCName = ontModel.getDatatypeProperty(OntologyController.URI + "testingSCName");
        DatatypeProperty testingSCPath = ontModel.getDatatypeProperty(OntologyController.URI + "testingSCPath");
        DatatypeProperty testingSuiteExeDuration = ontModel.getDatatypeProperty(OntologyController.URI + "testingSuiteExeDuration");
        DatatypeProperty testingSuiteExeFailed = ontModel.getDatatypeProperty(OntologyController.URI + "testingSuiteExeFailed");
        DatatypeProperty testingSuiteExeHealthScale = ontModel.getDatatypeProperty(OntologyController.URI + "testingSuiteExeHealthScale");
        DatatypeProperty testingSuiteExeId = ontModel.getDatatypeProperty(OntologyController.URI + "testingSuiteExeId");
        DatatypeProperty testingSuiteExeSkipped = ontModel.getDatatypeProperty(OntologyController.URI + "testingSuiteExeSkipped");
        DatatypeProperty testingSuiteExeSuccesfull = ontModel.getDatatypeProperty(OntologyController.URI + "testingSuiteExeSuccesfull");
        DatatypeProperty testingSuiteExeTotalCount = ontModel.getDatatypeProperty(OntologyController.URI + "testingSuiteExeTotalCount");
        DatatypeProperty testingSuiteId = ontModel.getDatatypeProperty(OntologyController.URI + "testingSuiteId");
        DatatypeProperty testingMethodExeId = ontModel.getDatatypeProperty(OntologyController.URI + "testingMethodExeId");
        DatatypeProperty testingMethodExeDuration = ontModel.getDatatypeProperty(OntologyController.URI + "testingMethodExeDuration");
        DatatypeProperty testingMethodExeResult = ontModel.getDatatypeProperty(OntologyController.URI + "testingMethodExeResult");
        DatatypeProperty testingMethodExeErrorDetails = ontModel.getDatatypeProperty(OntologyController.URI + "testingMethodExeErrorDetails");
        DatatypeProperty testingMethodExeErrorStrack = ontModel.getDatatypeProperty(OntologyController.URI + "testingMethodExeErrorStrack");
        DatatypeProperty testingMethodId = ontModel.getDatatypeProperty(OntologyController.URI + "testingMethodId");
        DatatypeProperty testingMethodName = ontModel.getDatatypeProperty(OntologyController.URI + "testingMethodName");
        DatatypeProperty testingMethodSkipped = ontModel.getDatatypeProperty(OntologyController.URI + "testingMethodSkipped");
        DatatypeProperty softwareArtifactId = ontModel.getDatatypeProperty(OntologyController.URI + "softwareArtifactId");
        DatatypeProperty testingArtifactId = ontModel.getDatatypeProperty(OntologyController.URI + "testingArtifactId");

        // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Loading data from database", "OK"));
        System.out.println("Loading data from database");

        //Carrega os Agentes na ontologia a partir do banco de dados
        Resource resourceagent = ontModel.getResource(OntologyController.URI + "Agent");
        List<Agent> agents = new AgentDAO().obterAgent();
        for (Agent agent : agents) {
            ontModel.createIndividual(OntologyController.URI + "Agent." + agent.getIdagent(), resourceagent);
            Individual ind = ontModel.getIndividual(OntologyController.URI + "Agent." + agent.getIdagent());
            ind.addProperty(agentId, agent.getIdagent(), XSDDatatype.XSDstring);
        }

        // Carregas as pessoas na ontologia a partir do banco de dados
        Resource resourcperson = ontModel.getResource(OntologyController.URI + "Person");
        List<Person> persons = new PersonDAO().obterPerson();
        for (Person person : persons) {
            ontModel.createIndividual(OntologyController.URI + "Person." + person.getIdperson(), resourcperson);
            Individual ind = ontModel.getIndividual(OntologyController.URI + "Person." + person.getIdperson());
            ind.addProperty(personId, person.getIdperson(), XSDDatatype.XSDstring);
            ind.addProperty(personName, person.getName(), XSDDatatype.XSDstring);
        }

        //Carrega as Atividades na ontologia a partir do banco de dados
        Resource resourceact = ontModel.getResource(OntologyController.URI + "Activity");
        List<Activity> activitys = new ActivityDAO().obterActivity();
        for (Activity activity : activitys) {
            ontModel.createIndividual(OntologyController.URI + "Activity." + activity.getIdactivity(), resourceact);
            Individual ind = ontModel.getIndividual(OntologyController.URI + "Activity." + activity.getIdactivity());
            ind.addProperty(activityId, activity.getIdactivity(), XSDDatatype.XSDstring);
        } 

        //Carrega as execuções do build na ontologia a partir do banco de dados
        Resource resourcebuildexe = ontModel.getResource(OntologyController.URI + "BuildExecution");
        List<Buildexecution> buildexecutions = new BuildExecutionDAO().obterBuildexecution();
        for (Buildexecution buildexecution : buildexecutions) {
            ontModel.createIndividual(OntologyController.URI + "BuildExecution." + buildexecution.getIdbuildExecution(), resourcebuildexe);
            Individual i = ontModel.getIndividual(OntologyController.URI + "BuildExecution." + buildexecution.getIdbuildExecution());
            i.addProperty(buildExeId, buildexecution.getIdbuildExecution(), XSDDatatype.XSDstring);
            i.addProperty(buildExeResult, buildexecution.getResult(), XSDDatatype.XSDstring);
            i.addProperty(buildExeWorkspace, buildexecution.getWorkspace(), XSDDatatype.XSDstring);
            i.addProperty(buildExeDuration, buildexecution.getDuration().toString(), XSDDatatype.XSDinteger);

        }

        //Carrega os commits efetuados na ontologia a partir do banco de dados
        Resource resourceBE = ontModel.getResource(OntologyController.URI + "CommitingActivity");
        List<Commitactivity> commitactivitys = new CommitDAO().obterCommitActivity();
        String commitNull = "null";
        for (Commitactivity commitactivity : commitactivitys) {
            ontModel.createIndividual(OntologyController.URI + "CommitingActivity." + commitactivity.getIdcommitActivity(), resourceBE);
            Individual a = ontModel.getIndividual(OntologyController.URI + "CommitingActivity." + commitactivity.getIdcommitActivity());
            a.addProperty(commitId, commitactivity.getIdcommitActivity(), XSDDatatype.XSDstring);
            if (commitactivity.getCommit() == null) {
                a.addProperty(commit, commitNull, XSDDatatype.XSDstring);
            } else {
                a.addProperty(commit, commitactivity.getCommit(), XSDDatatype.XSDstring);
            }
            if (commitactivity.getAuthor() == null) {
                a.addProperty(commitAuthor, commitNull, XSDDatatype.XSDstring);
            } else {
                a.addProperty(commitAuthor, commitactivity.getAuthor(), XSDDatatype.XSDstring);
            }
            if (commitactivity.getCommiter() == null) {
                a.addProperty(commiter, commitNull, XSDDatatype.XSDstring);
            } else {
                a.addProperty(commiter, commitactivity.getCommiter(), XSDDatatype.XSDstring);
            }
            if (commitactivity.getHour() == null) {
                a.addProperty(commitHour, commitNull, XSDDatatype.XSDstring);
            } else {
                a.addProperty(commitHour, commitactivity.getHour(), XSDDatatype.XSDstring);
            }
            if (commitactivity.getParent() == null) {
                a.addProperty(commitParent, commitNull, XSDDatatype.XSDstring);
            } else {
                a.addProperty(commitParent, commitactivity.getParent(), XSDDatatype.XSDstring);
            }
            if (commitactivity.getTree() == null) {
                a.addProperty(commitTree, commitNull, XSDDatatype.XSDstring);
            } else {
                a.addProperty(commitTree, commitactivity.getTree(), XSDDatatype.XSDstring);
            }
            if (commitactivity.getDate() == null) {
                a.addProperty(commitDate, commitNull, XSDDatatype.XSDstring);
            } else {
                a.addProperty(commitDate, commitactivity.getDate(), XSDDatatype.XSDstring);
            }
        }

        //Carrega as suites de testes executadas  na ontologia a partir do banco de dados
        Resource resourceTSE = ontModel.getResource(OntologyController.URI + "TestingSuiteExecution");
        List<Testingsuiteexecution> testingsuiteexecutions = new TestingSuiteExecutionDAO().obterTestingsuiteexecution();
        for (Testingsuiteexecution testingsuiteexecution : testingsuiteexecutions) {
            ontModel.createIndividual(OntologyController.URI + "TestingSuiteExecution." + testingsuiteexecution.getIdtestingSuiteExecution(), resourceTSE);
            Individual a = ontModel.getIndividual(OntologyController.URI + "TestingSuiteExecution." + testingsuiteexecution.getIdtestingSuiteExecution());
            a.addProperty(testingSuiteExeId, testingsuiteexecution.getIdtestingSuiteExecution(), XSDDatatype.XSDstring);
            a.addProperty(testingSuiteExeDuration, testingsuiteexecution.getDuration().toString(), XSDDatatype.XSDfloat);
            a.addProperty(testingSuiteExeSuccesfull, testingsuiteexecution.getSuccesfullTest().toString(), XSDDatatype.XSDinteger);
            a.addProperty(testingSuiteExeFailed, testingsuiteexecution.getFailedTest().toString(), XSDDatatype.XSDinteger);
            a.addProperty(testingSuiteExeSkipped, testingsuiteexecution.getSkippedTest().toString(), XSDDatatype.XSDinteger);
            a.addProperty(testingSuiteExeTotalCount, testingsuiteexecution.getTotalCount().toString(), XSDDatatype.XSDinteger);
            a.addProperty(testingSuiteExeHealthScale, testingsuiteexecution.getHealthScale().toString(), XSDDatatype.XSDdouble);

        }

        //Carrega as execuções das classes do build na ontologia a partir do banco de dados
        Resource resourceTCE = ontModel.getResource(OntologyController.URI + "TestingClassExecution");
        List<Testingclassexecution> testingclassexecutions = new TestingClassExecutionDAO().obterTestingclassexecution();
        for (Testingclassexecution testingclassexecution : testingclassexecutions) {
            ontModel.createIndividual(OntologyController.URI + "TestingClassExecution." + testingclassexecution.getIdtestingClassExecution(), resourceTCE);
            Individual a = ontModel.getIndividual(OntologyController.URI + "TestingClassExecution." + testingclassexecution.getIdtestingClassExecution());
            a.addProperty(testingClassExeId, testingclassexecution.getIdtestingClassExecution().toString(), XSDDatatype.XSDinteger);
            a.addProperty(testingClassExeExecutionTime, testingclassexecution.getExecutionTime().toString(), XSDDatatype.XSDfloat);
        }

        //Carrega as execuções dos métodos de teste na ontologia a partir do banco de dados
        Resource resourceTME = ontModel.getResource(OntologyController.URI + "TestingMethodExecution");
        List<Testingmethodexecution> testingMethodExecuiton = new TestingMethodExecutionDAO().obterTestingmethodexecution();
        for (Testingmethodexecution testingmethodExe : testingMethodExecuiton) {
            ontModel.createIndividual(OntologyController.URI + "TestingMethodExecution." + testingmethodExe.getIdtestingMethodExection(), resourceTME);
            Individual a = ontModel.getIndividual(OntologyController.URI + "TestingMethodExecution." + testingmethodExe.getIdtestingMethodExection());
            a.addProperty(testingMethodExeId, testingmethodExe.getIdtestingMethodExection().toString(), XSDDatatype.XSDinteger);
            a.addProperty(testingMethodExeDuration, testingmethodExe.getDurationTime().toString(), XSDDatatype.XSDfloat);
            a.addProperty(testingMethodExeResult, testingmethodExe.getResult(), XSDDatatype.XSDstring);
            if (testingmethodExe.getErrorDatails() == null) {
                String ErrorDatails = "null";
                a.addProperty(testingMethodExeErrorDetails, ErrorDatails, XSDDatatype.XSDstring);
            } else {
                a.addProperty(testingMethodExeErrorDetails, testingmethodExe.getErrorDatails(), XSDDatatype.XSDstring);
            }
            if (testingmethodExe.getErrorStrackTrace() == null) {
                String ErrorStrackTrace = "null";
                a.addProperty(testingMethodExeErrorStrack, ErrorStrackTrace, XSDDatatype.XSDstring);
            } else {
                a.addProperty(testingMethodExeErrorStrack, testingmethodExe.getErrorStrackTrace(), XSDDatatype.XSDstring);
            }
        } 
                //Carrega as Entidades na ontologia a partir do banco de dados
                Resource resourceent = ontModel.getResource(OntologyController.URI + "Entity");
        List<Entityprov> entitys = EntityDAO.obterEntity();
        for (Entityprov entity : entitys) {
            ontModel.createIndividual(OntologyController.URI + "Entity." + entity.getIdentity(), resourceent);
            Individual ind = ontModel.getIndividual(OntologyController.URI + "Entity." + entity.getIdentity());
            ind.addProperty(entityId, entity.getIdentity(), XSDDatatype.XSDstring);
            ind.addProperty(entityName, entity.getName(), XSDDatatype.XSDstring);
        }

        //Carrega os projetos na ontologia a partir do banco de dados
        Resource resourceProject = ontModel.getResource(OntologyController.URI + "Project");
        List<Project> projects = new ProjectDAO().obterProject();
        for (Project project : projects) {
            ontModel.createIndividual(OntologyController.URI + "Project." + project.getIdproject(), resourceProject);
            Individual a = ontModel.getIndividual(OntologyController.URI + "Project." + project.getIdproject());
            a.addProperty(projectId, project.getIdproject(), XSDDatatype.XSDstring);
            a.addProperty(projectName, project.getName(), XSDDatatype.XSDstring);
            a.addProperty(projectVersion, project.getProjectVersion(), XSDDatatype.XSDstring);
        }

        //Carrega os artefatos dos softwares na ontologia a partir do banco de dados
        Resource resourceSA = ontModel.getResource(OntologyController.URI + "SoftwareArtifact");
        List<Softwareartifact> softwareAftifact = new SoftwareArtifactDAO().obterSoftwareartifact();
        for (Softwareartifact softwarear : softwareAftifact) {
            ontModel.createIndividual(OntologyController.URI + "SoftwareArtifact." + softwarear.getIdsoftwareArtifact(), resourceSA);
            Individual a = ontModel.getIndividual(OntologyController.URI + "SoftwareArtifact." + softwarear.getIdsoftwareArtifact());
            a.addProperty(softwareArtifactId, softwarear.getIdsoftwareArtifact(), XSDDatatype.XSDstring);
        }

        //Carrega o software build na ontologia a partir do banco de dados
        Resource resourceSB = ontModel.getResource(OntologyController.URI + "SoftwareBuild");
        List<Softwarebuild> softwarebuilds = new SoftwareBuildDAO().obterSoftwarebuild();
        for (Softwarebuild softwarebuild : softwarebuilds) {
            ontModel.createIndividual(OntologyController.URI + "SoftwareBuild." + softwarebuild.getIdsoftwareBuild(), resourceSB);
            Individual a = ontModel.getIndividual(OntologyController.URI + "SoftwareBuild." + softwarebuild.getIdsoftwareBuild());
            a.addProperty(softwareBuildId, softwarebuild.getIdsoftwareBuild(), XSDDatatype.XSDstring);
            a.addProperty(softwareBuildDuration, softwarebuild.getDurationBuild().toString(), XSDDatatype.XSDdouble);
            //a.addProperty(startedAtTime, softwarebuild.getStartBuild());
        }

        //Carrega o códigos na ontologia a partir do banco de dados
        Resource resourceSC = ontModel.getResource(OntologyController.URI + "SourceCode");
        List<Sourcecode> sourcecodes = new SourceCodeDAO().obterSourcecode();
        for (Sourcecode sourcecode : sourcecodes) {
            ontModel.createIndividual(OntologyController.URI + "SourceCode." + sourcecode.getIdsourceCode(), resourceSC);
            Individual a = ontModel.getIndividual(OntologyController.URI + "SourceCode." + sourcecode.getIdsourceCode());
            a.addProperty(sourceCodeId, sourcecode.getIdsourceCode().toString(), XSDDatatype.XSDinteger);
            a.addProperty(sourceCodeName, sourcecode.getName(), XSDDatatype.XSDstring);
            a.addProperty(sourceCodePath, sourcecode.getPath(), XSDDatatype.XSDstring);
        }

              //Carrega o software exacutable na ontologia a partir do banco de dados
        Resource resourceSE = ontModel.getResource(OntologyController.URI + "SoftwareExecutable");
        List<Softwareexecutable> softwareexecutables = new SoftwareExecutableDAO().obterSoftwareexecutable();
        for (Softwareexecutable softwareexecutable : softwareexecutables) {
            ontModel.createIndividual(OntologyController.URI + "SoftwareExecutable." + softwareexecutable.getIdsoftwareExecutable(), resourceSE);
            Individual a = ontModel.getIndividual(OntologyController.URI + "SoftwareExecutable." + softwareexecutable.getIdsoftwareExecutable());
            a.addProperty(softwareExecutableId, softwareexecutable.getIdsoftwareExecutable(), XSDDatatype.XSDstring);
            a.addProperty(softwareExecutableName, softwareexecutable.getName(), XSDDatatype.XSDstring);
        }  

        //Carrega os artefatos dos softwares na ontologia a partir do banco de dados
        Resource resourceTA = ontModel.getResource(OntologyController.URI + "TestingArtifact");
        List<Testingartifact> testingAftifact = new TestingArtifactDAO().obterTestingartifact();
        for (Testingartifact testingart : testingAftifact) {
            ontModel.createIndividual(OntologyController.URI + "TestingArtifact." + testingart.getIdtestingArtifact(), resourceTA);
            Individual a = ontModel.getIndividual(OntologyController.URI + "TestingArtifact." + testingart.getIdtestingArtifact());
            a.addProperty(testingArtifactId, testingart.getIdtestingArtifact(), XSDDatatype.XSDstring);
        } 
        
        //Carrega os logs de teste gerados na ontologia a partir do banco de dados
        Resource resourceTL = ontModel.getResource(OntologyController.URI + "TestingLog");
        List<Testinglog> testinglogs = new TestingLogDAO().obterTestinglog();
        for (Testinglog testinglog : testinglogs) {
            ontModel.createIndividual(OntologyController.URI + "TestingLog." + testinglog.getIdtestingLog(), resourceTL);
            Individual a = ontModel.getIndividual(OntologyController.URI + "TestingLog." + testinglog.getIdtestingLog());
            a.addProperty(testingLogId, testinglog.getIdtestingLog(), XSDDatatype.XSDstring);
            a.addProperty(testingLogPath, testinglog.getPath(), XSDDatatype.XSDstring);
            a.addProperty(testingLogGenerationDate, testinglog.getGenerationDateTime(), XSDDatatype.XSDstring);
        }

        
        //Carrega o códigos de teste na ontologia a partir do banco de dados
        Resource resourceTSC = ontModel.getResource(OntologyController.URI + "TestingSourceCode");
        List<Testingsourcecode> testingsourcecodes = new TestingSourceCodeDAO().obterTestingsourcecode();
        for (Testingsourcecode testingsourcecode : testingsourcecodes) {
            ontModel.createIndividual(OntologyController.URI + "TestingSourceCode." + testingsourcecode.getIdtestingSourceCode(), resourceTSC);
            Individual a = ontModel.getIndividual(OntologyController.URI + "TestingSourceCode." + testingsourcecode.getIdtestingSourceCode());
            a.addLiteral(testingSCId, testingsourcecode.getIdtestingSourceCode());
            a.addProperty(testingSCName, testingsourcecode.getName(), XSDDatatype.XSDstring);
            a.addProperty(testingSCPath, testingsourcecode.getPath(), XSDDatatype.XSDstring);
        } 
     
        //Carrega os projetos na ontologia a partir do banco de dados
        Resource resourceE = ontModel.getResource(OntologyController.URI + "Environment");
        List<Environment> environments = new EnvironmentDAO().obterEnvironment();
        for (Environment environment : environments) {
            ontModel.createIndividual(OntologyController.URI + "Environment." + environment.getIdenvironment(), resourceE);
            Individual a = ontModel.getIndividual(OntologyController.URI + "Environment." + environment.getIdenvironment());
            a.addProperty(environmentId, environment.getIdenvironment(), XSDDatatype.XSDstring);
            a.addProperty(environmentJavaClassVersion, environment.getJavaClassversion(), XSDDatatype.XSDstring);
            a.addProperty(environmentJavaRuntimeClass, environment.getJavaRuntimeName(), XSDDatatype.XSDstring);
            a.addProperty(environmentJavaRuntimeVersion, environment.getJavaRuntimeVersion(), XSDDatatype.XSDstring);
            a.addProperty(environmentJavaSpecificationName, environment.getJavaSpecificationName(), XSDDatatype.XSDstring);
            a.addProperty(environmentJavaSpecificationVersion, environment.getJavaVmSpecificationVersion(), XSDDatatype.XSDstring);
            a.addProperty(environmentVmVersion, environment.getJavaVmVersion(), XSDDatatype.XSDstring);
            a.addProperty(environmentOsName, environment.getOsName(), XSDDatatype.XSDstring);
            a.addProperty(environmentOsVersion, environment.getOsVersion(), XSDDatatype.XSDstring);
        }

        //Carrega a suite de teste na ontologia a partir do banco de dados
        Resource resourceTS = ontModel.getResource(OntologyController.URI + "TestingSuite");
        List<Testingsuite> testingsuites = new TestingSuiteDAO().obterTestingsuite();
        for (Testingsuite testingsuite : testingsuites) {
            ontModel.createIndividual(OntologyController.URI + "TestingSuite." + testingsuite.getIdtestingSuite(), resourceTS);
            Individual a = ontModel.getIndividual(OntologyController.URI + "TestingSuite." + testingsuite.getIdtestingSuite());
            a.addProperty(testingSuiteId, testingsuite.getIdtestingSuite(), XSDDatatype.XSDstring);
        }

        //Carrega a classe de teste na ontologia a partir do banco de dados
        Resource resourceTC = ontModel.getResource(OntologyController.URI + "TestingClass");
        List<Testingclass> testingclasss = new TestingClassDAO().obterTestingclass();
        for (Testingclass testingclass : testingclasss) {
            ontModel.createIndividual(OntologyController.URI + "TestingClass." + testingclass.getIdtestingClass(), resourceTC);
            Individual a = ontModel.getIndividual(OntologyController.URI + "TestingClass." + testingclass.getIdtestingClass());
            a.addProperty(testingClassId, testingclass.getIdtestingClass().toString(), XSDDatatype.XSDinteger);
            a.addProperty(testingClassName, testingclass.getName(), XSDDatatype.XSDstring);
        }

        //Carrega as execuções dos métodos de teste na ontologia a partir do banco de dados
        Resource resourceTM = ontModel.getResource(OntologyController.URI + "TestingMethod");
        List<Testingmethod> testingMethod = new TestingMethodDAO().obterTestingmethod();
        for (Testingmethod testingmethod : testingMethod) {
            ontModel.createIndividual(OntologyController.URI + "TestingMethod." + testingmethod.getIdtestingMethod(), resourceTM);
            Individual a = ontModel.getIndividual(OntologyController.URI + "TestingMethod." + testingmethod.getIdtestingMethod());
            a.addProperty(testingMethodId, testingmethod.getIdtestingMethod().toString(), XSDDatatype.XSDinteger);
            a.addProperty(testingMethodName, testingmethod.getName(), XSDDatatype.XSDstring);
            a.addProperty(testingMethodSkipped, testingmethod.getSkipped(), XSDDatatype.XSDstring);
        }

        // Carrega a HasEnvironment propriedade na ontologia
               List<Hasenvironment> Listwasenvironment = new HasenvironmentDAO().obterHasenvironment();
        for (Hasenvironment hasenvironment : Listwasenvironment) {
            Individual i = ontModel.getIndividual(OntologyController.URI + "Environment." + hasenvironment.getEnvironment().getIdenvironment());
            if (hasenvironment.getBuildexecution() != null) {
                Individual o = ontModel.getIndividual(OntologyController.URI + "BuildExecution." + hasenvironment.getBuildexecution().getIdbuildExecution());
                o.addProperty(hasEnvironment, i);
            } else {
                Individual o = ontModel.getIndividual(OntologyController.URI + "TestingSuiteExecution." + hasenvironment.getTestingsuiteexecution().getIdtestingSuiteExecution());
                o.addProperty(hasEnvironment, i);
            }
        }

        // Carrega a Actedonbehalfof propriedade na ontologia
        List<Actedonbehalfof> ListActedonbehalfof = new ActedonbehalfofDAO().obterActedonbehalfof();
        for (Actedonbehalfof actedonbehalfof : ListActedonbehalfof) {
            Individual i = ontModel.getIndividual(OntologyController.URI + "Agent." + actedonbehalfof.getAgentByAgentIdagent().getIdagent());
            Individual o = ontModel.getIndividual(OntologyController.URI + "Agent." + actedonbehalfof.getAgentByAgentIdagent().getIdagent());
            o.addProperty(actedOnBehalfOf, i);
        }

        // Carrega a Wasassociatedwith propriedade na ontologia
        List<Wasassociatedwith> ListWasassociatedwith = new WasassociatedwithDAO().obterWasassociatedwith();
        for (Wasassociatedwith wasassociatedwith : ListWasassociatedwith) {
            Individual i = ontModel.getIndividual(OntologyController.URI + "Person." + wasassociatedwith.getPerson().getIdperson());
            if (wasassociatedwith.getBuildexecution() != null) {
                Individual o = ontModel.getIndividual(OntologyController.URI + "BuildExecution." + wasassociatedwith.getBuildexecution().getIdbuildExecution());
                o.addProperty(wasAssociateWith, i);
            } else {
                if (wasassociatedwith.getCommitactivity() != null) {
                    Individual o = ontModel.getIndividual(OntologyController.URI + "CommitingActivity." + wasassociatedwith.getCommitactivity().getIdcommitActivity());
                    o.addProperty(wasAssociateWith, i);
                } else {
                    Individual o = ontModel.getIndividual(OntologyController.URI + "TestingSuiteExecution." + wasassociatedwith.getTestingsuiteexecution().getIdtestingSuiteExecution());
                    o.addProperty(wasAssociateWith, i);
                }

            }

        }

        // Carrega a Wasinformedby propriedade na ontologia
        List<Wasinformedby> ListWasinformedby = new WasinformedbyDAO().obterWasinformedby();
        for (Wasinformedby wasinformedby : ListWasinformedby) {
            Individual i = ontModel.getIndividual(OntologyController.URI + "TestingClassExecution." + wasinformedby.getTestingclassexecution().getIdtestingClassExecution());
            if (wasinformedby.getTestingsuiteexecution() != null) {
                Individual o = ontModel.getIndividual(OntologyController.URI + "TestingSuiteExecution." + wasinformedby.getTestingsuiteexecution().getIdtestingSuiteExecution());
                o.addProperty(wasInformedBy, i);
            } else {
                Individual o = ontModel.getIndividual(OntologyController.URI + "TestingMethodExecution." + wasinformedby.getTestingmethodexecution().getIdtestingMethodExection());
                i.addProperty(wasAssociateWith, o);
            }

        }

        //Carrega a Used property na ontologia
        List<Used> Listused = new UsedDAO().obterUsed();
        for (Used usedObj : Listused) {
            if (usedObj.getBuildexecution() != null) {
                // Individual i = ontModel.getIndividual(OntologyController.URI + "BuildExecution." + usedObj.getBuildexecution().getIdbuildExecution());
                //  Individual o = ontModel.getIndividual(OntologyController.URI + "SoftwareArtifact." + usedObj.getSoftwareartifact().getIdsoftwareArtifact());
                //  i.addProperty(used, o);
            } else {
                if (usedObj.getTestingsuiteexecution() != null) {
                    Individual i = ontModel.getIndividual(OntologyController.URI + "TestingSuiteExecution." + usedObj.getTestingsuiteexecution().getIdtestingSuiteExecution());
                    Individual o = ontModel.getIndividual(OntologyController.URI + "TestingSuite." + usedObj.getTestingsuite().getIdtestingSuite());
                    i.addProperty(used, o);
                } else {
                    if (usedObj.getTestingclassexecution() != null) {
                        Individual i = ontModel.getIndividual(OntologyController.URI + "TestingClassExecution." + usedObj.getTestingclassexecution().getIdtestingClassExecution());
                        Individual o = ontModel.getIndividual(OntologyController.URI + "TestingClass." + usedObj.getTestingclass().getIdtestingClass());
                        i.addProperty(used, o);
                    } else {
                        Individual i = ontModel.getIndividual(OntologyController.URI + "TestingMethodExecution." + usedObj.getTestingmethodexecution().getIdtestingMethodExection());
                        Individual o = ontModel.getIndividual(OntologyController.URI + "TestingMethod." + usedObj.getTestingmethod().getIdtestingMethod());
                        i.addProperty(used, o);
                    }

                }
            }
        }

        // Carrega a Actedonbehalfof propriedade na ontologia
        List<Composedof> ListComposedof = new ComposedofDAO().obterComposedof();
        for (Composedof composedof : ListComposedof) {
            Individual o = ontModel.getIndividual(OntologyController.URI + "TestingSuite." + composedof.getTestingsuite().getIdtestingSuite());
            Individual i = ontModel.getIndividual(OntologyController.URI + "TestingSourceCode." + composedof.getTestingsourcecode().getIdtestingSourceCode());
            o.addProperty(composedOf, i);
        }
        
        // Carrega a propriedade wasAttributesdTo na ontologia
        List<Wasattributedto> ListwasattributedTo = new WasattributedtoDAO().obterWasattributedto();
        for (Wasattributedto wasattributedto : ListwasattributedTo) {
            Individual i = ontModel.getIndividual(OntologyController.URI + "Person." + wasattributedto.getPerson().getIdperson());
            Individual o = ontModel.getIndividual(OntologyController.URI + "Project." + wasattributedto.getProject().getIdproject());
            o.addProperty(wasAttributedTo, i);
        }

        // Carrega a Wasgeneratedby propriedade na ontologia
        List<Wasgeneratedby> ListWasGeneratedBy = new WasgeneratedbyDAO().obterWasgeneratedby();
        for (Wasgeneratedby wasgeneratedby : ListWasGeneratedBy) {
            if (wasgeneratedby.getSoftwareexecutable() != null) {
                Individual i = ontModel.getIndividual(OntologyController.URI + "SoftwareExecutable." + wasgeneratedby.getSoftwareexecutable().getIdsoftwareExecutable());
                Individual o = ontModel.getIndividual(OntologyController.URI + "BuildExecution." + wasgeneratedby.getBuildexecution().getIdbuildExecution());
                i.addProperty(wasGeneratedBy, o);
            } else {
                if (wasgeneratedby.getSoftwarebuild() != null) {
                    Individual i = ontModel.getIndividual(OntologyController.URI + "SoftwareBuild." + wasgeneratedby.getSoftwarebuild().getIdsoftwareBuild());
                    Individual o = ontModel.getIndividual(OntologyController.URI + "BuildExecution." + wasgeneratedby.getBuildexecution().getIdbuildExecution());
                    i.addProperty(wasGeneratedBy, o);
                } else {
                    Individual i = ontModel.getIndividual(OntologyController.URI + "TestingLog." + wasgeneratedby.getTestinglog().getIdtestingLog());
                    Individual o = ontModel.getIndividual(OntologyController.URI + "TestingSuiteExecution." + wasgeneratedby.getTestingsuiteexecution().getIdtestingSuiteExecution());
                    i.addProperty(wasGeneratedBy, o);
                }
            }
        }

        // Carrega a Wasderivedfrom propriedade na ontologia
        List<Wasderivedfrom> ListWasderivedfrom = new WasderivedfromDAO().obterWasderivedfrom();
        for (Wasderivedfrom wasderivedfrom : ListWasderivedfrom) {
            if (wasderivedfrom.getSoftwarebuild() != null) {
                Individual i = ontModel.getIndividual(OntologyController.URI + "SoftwareBuild." + wasderivedfrom.getSoftwarebuild().getIdsoftwareBuild());
                Individual o = ontModel.getIndividual(OntologyController.URI + "SoftwareArtifact." + wasderivedfrom.getSoftwareartifact().getIdsoftwareArtifact());
                i.addProperty(wasDerivedFrom, o);
            } else {
                if (wasderivedfrom.getTestinglog() != null) {
                    Individual i = ontModel.getIndividual(OntologyController.URI + "TestingLog." + wasderivedfrom.getTestinglog().getIdtestingLog());
                    Individual o = ontModel.getIndividual(OntologyController.URI + "TestingSuite." + wasderivedfrom.getTestingsuite().getIdtestingSuite());
                    i.addProperty(wasDerivedFrom, o);
                } else {
                    if (wasderivedfrom.getSoftwareexecutable() != null) {
                        Individual i = ontModel.getIndividual(OntologyController.URI + "SoftwareExecutable." + wasderivedfrom.getSoftwareexecutable().getIdsoftwareExecutable());
                        Individual o = ontModel.getIndividual(OntologyController.URI + "SoftwareArtifact." + wasderivedfrom.getSoftwareartifact().getIdsoftwareArtifact());
                        i.addProperty(wasDerivedFrom, o);
                    } else {
                        Individual i = ontModel.getIndividual(OntologyController.URI + "TestingSourceCode." + wasderivedfrom.getTestingsourcecode().getIdtestingSourceCode());
                        Individual o = ontModel.getIndividual(OntologyController.URI + "SourceCode." + wasderivedfrom.getSourcecode().getIdsourceCode());
                        i.addProperty(wasDerivedFrom, o);
                    }

                }
            }
        }

        List<Covers> ListCovers = new CoversDAO().obterCovers();
        for (Covers coversObj : ListCovers) {
            Individual i = ontModel.getIndividual(OntologyController.URI + "TestingMethod." + coversObj.getTestingmethod().getIdtestingMethod());
            Individual o = ontModel.getIndividual(OntologyController.URI + "SourceCode." + coversObj.getSourcecode().getIdsourceCode());
            i.addProperty(wasDerivedFrom, o);
        }  
        //validar a nova ontologia a ser criada
        System.out.println("Validating the loadOntology");
        //    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Validating the ontology", "Ok"));

        //Gerar o novo arquivo com os dados do banco na nova ontologia
        FileWriter arquivo = null;
        try {
            //caminho para o novo arquivo de ontologia
            arquivo = new FileWriter(OntologyController.ONTOLOGY_LOAD);
            //se não existir arquivo, o mesmo será criado, senão, será reescrito
        } catch (IOException ex) {
            ex.printStackTrace(); //verificando problemas
        }
        //determinando que o fluxo de saida vai para o arquivo e não para a tela            
        BufferedWriter out = new BufferedWriter(arquivo);
        //ontologia carregada
        ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ontModel);
        //utilizar RDF/XML-ABBREV, so RDF/XML da erro no protege!        
        ontModel.write(out);
        //   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ontology successfully loaded", "OK"));

    }

}
