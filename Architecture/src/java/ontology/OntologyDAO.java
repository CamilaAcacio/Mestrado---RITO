/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontology;

import model.Project;
import dao.ProjectDAO;
import model.Entityprov;
import dao.EntityDAO;
import java.sql.SQLException;
import model.Activity;
import model.Agent;
import dao.AgentDAO;
import dao.WasassociatedwithDAO;
import model.Buildexecution;
import model.Commitactivity;
import dao.CommitDAO;
import dao.EnvironmentDAO;
import dao.PersonDAO;
import dao.SoftwareBuildDAO;
import dao.SoftwareExecutableDAO;
import dao.SourceCodeDAO;
import dao.TestingClassDAO;
import dao.TestingClassExecutionDAO;
import dao.TestingLogDAO;
import dao.TestingSourceCodeDAO;
import dao.TestingSuiteDAO;
import dao.TestingSuiteExecutionDAO;
import model.Environment;
import model.Person;
import model.Softwarebuild;
import model.Softwareexecutable;
import model.Sourcecode;
import model.Testingclass;
import model.Testingclassexecution;
import model.Testinglog;
import model.Testingsourcecode;
import model.Testingsuite;
import model.Testingsuiteexecution;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.reasoner.ValidityReport;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import dao.ActedonbehalfofDAO;
import dao.ActivityDAO;
import dao.UsedDAO;
import dao.WasderivedfromDAO;
import dao.WasgeneratedbyDAO;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Actedonbehalfof;
import model.Used;
import model.Wasassociatedwith;
import model.Wasderivedfrom;
import model.Wasgeneratedby;

public class OntologyDAO {

    //caminho fisico da ontology
    private final String ontology = "../../files/ontologies/prov-oext.owl";
    //caminho fisico da nova ontology
    private final String loadOntology = "../../files/ontologies/prov-oextload.owl";

    public void loadDAO() throws ClassNotFoundException, SQLException {

        //variavel global
        OntModel model;
        //uri da ontology
        String baseURI = "http://www.w3.org/ns/prov#";

        //inicia a maquina de inferencia e carrega a ontology nela
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.read(ontology);
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
        reasoner = reasoner.bindSchema(ontModel);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM_TRANS_INF;
        ontModelSpec.setReasoner(reasoner);
        //ontologia carregada na máquina de inferencia
        model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Loading data from database", "OK"));
        //System.out.println("Loading data from database");

        //Prepara os objectsProperties
        ObjectProperty aoboop = ontModel.createObjectProperty(baseURI + "ActedOnBehalfOf");
        ObjectProperty wgbop = ontModel.createObjectProperty(baseURI + "wasGeneratedBy");
        ObjectProperty usedope = ontModel.createObjectProperty(baseURI + "Used");
        ObjectProperty wawop = ontModel.createObjectProperty(baseURI + "wasAssociatedWith");

        //Carrega os Entitys na ontology apartir do banco de dados
        Resource resourceorg = model.getResource(baseURI + "Entityprov");
        Entityprov entity = new Entityprov();
        List entitys = new ArrayList();
        entitys = new EntityDAO().obterEntity();
        for (Object entity1 : entitys) {
            entity = (Entityprov) entity1;
            model.createIndividual(baseURI + entity.getIdentity(), resourceorg);
        }

        //Carrega os Agents na ontology apartir do banco de dados
        Resource resourceperson = model.getResource(baseURI + "Agent");
        Agent agent = new Agent();
        List agents = new ArrayList();
        agents = new AgentDAO().obterAgent();
        for (Object agent1 : agents) {
            agent = (Agent) agent1;
            model.createIndividual(baseURI + agent.getIdagent(), resourceperson);
        }

        //Carrega a associação do experimento com o grupo de pesquisa na ontology apartir do banco de dados
        Wasgeneratedby wgb = new Wasgeneratedby();
        List wgbs = new ArrayList();
        wgbs = new WasgeneratedbyDAO().obterWasgeneratedby();
        for (Object wgb1 : wgbs) {
            wgb = (Wasgeneratedby) wgb1;
//            Individual exp = model.getIndividual(baseURI + wgb.getEntityprov().getIdentity());
 //           Individual rg = model.getIndividual(baseURI + wgb.getActivity().getIdactivity());
   //         exp.addProperty(wgbop, rg);
        }

        //Carrega os Activitys na ontology apartir do banco de dados
        Resource resourceact = model.getResource(baseURI + "Activity");
        Activity activity = new Activity();
        List activitys = new ArrayList();
        activitys = new ActivityDAO().obterActivity();
        for (Object activity1 : activitys) {
            activity = (Activity) activity1;
            model.createIndividual(baseURI + activity.getIdactivity(), resourceact);
        }

        //Carrega os dados do Used na ontology apartir do banco de dados
        Used used = new Used();
        List useds = new ArrayList();
        useds = new UsedDAO().obterUsed();
        for (Object used1 : useds) {
            used = (Used) used1;
            Individual t = model.getIndividual(baseURI + used.getIdused());
 //           Individual w = model.getIndividual(baseURI + used.getActivity().getIdactivity());
 //           w.addProperty(usedope, t);
        }

        // faz a associação entre workflow e experiemnto
        Wasassociatedwith waw = new Wasassociatedwith();
        List waws = new ArrayList();
        waws = new WasassociatedwithDAO().obterWasassociatedwith();
        for (Object waw1 : waws) {
            waw = (Wasassociatedwith) waw1;
//            Individual w = model.getIndividual(baseURI + waw.getAgent().getIdagent());
//            Individual exp = model.getIndividual(baseURI + waw.getActivity().getIdactivity());
//            exp.addProperty(wawop, w);
        }

        Actedonbehalfof aobo = new Actedonbehalfof();
        List aobos = new ArrayList();
        aobos = new ActedonbehalfofDAO().obterActedonbehalfof();
        for (Object aobo1 : aobos) {
            aobo = (Actedonbehalfof) aobo1;
            Resource resourceop = null;
            Individual i = model.createIndividual(baseURI + aobo.getAgentByAgentIdagent(), resourceop);
            Individual o = model.getIndividual(baseURI + aobo.getAgentByAgentIdagent().getIdagent());
            o.addProperty(aoboop, i);
        }

        //validar a nova ontology a ser criada
        //System.out.println("Validating the loadOntology");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Validating the ontology", "Ok"));
        InfModel modelInf = ModelFactory.createInfModel(reasoner, model);
        ValidityReport vrp1 = modelInf.validate();
        if (vrp1.isValid()) {
            //System.out.println("Valid OWL");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Valid OWL", "Yes"));
        } else {
            //System.out.println("Not valid OWL");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Not valid OWL", "Sorry, a failure occurred"));
            for (Iterator i = vrp1.getReports(); i.hasNext();) {
                //System.out.println(" - " + i.next());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(" - " + i.next()));
            }
        }

        //Gerar o novo arquivo com os dados do banco na nova ontology
        FileWriter arquivo = null;
        try {
            //caminho para o novo arquivo de ontology
            arquivo = new FileWriter(loadOntology);
            //se não existir arquivo, o mesmo será criado, se não, será reescrito
        } catch (IOException ex) {
            ex.printStackTrace(); //verificando problemas
        }
        //determinando que o fluxo de saida vai para o arquivo e não para a tela            
        BufferedWriter out = new BufferedWriter(arquivo);
        //ontologia carregada na máquina de inferencia
        model = ModelFactory.createOntologyModel(ontModelSpec, model);
        //utilizar RDF/XML-ABBREV, so RDF/XML da erro no protege!        
        model.write(out, "RDF/XML-ABBREV");

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ontology successfully loaded", "OK"));

    }

    /*  public List<String> buscartodos() {
        //variavel global
        OntModel model;
        //uri da ontology
        String baseURI = "http://www.w3.org/ns/prov#";

        //inicia a maquina de inferencia e carrega a ontology nela
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.read(loadOntology);
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
        reasoner = reasoner.bindSchema(ontModel);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;
        ontModelSpec.setReasoner(reasoner);
        //ontologia carregada na máquina de inferencia
        model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);

        List ontologys = new ArrayList();
        OntClass equipe = model.getOntClass(baseURI + "Workflow");
        OntProperty nome = model.getOntProperty(baseURI + "Used");
        String temp1;
        String temp2;
        for (ExtendedIterator<? extends OntResource> instances = equipe.listInstances(); instances.hasNext();) {
            OntResource equipeInstance = instances.next();
            ontologys.add(equipeInstance.getProperty(nome).toString().replace(baseURI, ""));
            //System.out.println("Equipe instance: " + equipeInstance.getProperty(nome).toString().replace("http://www.w3.org/ns/prov#", ""));

            // find out the resources that link to the instance
            for (StmtIterator stmts = model.listStatements(null, null, equipeInstance); stmts.hasNext();) {
                Individual ind = stmts.next().getSubject().as(Individual.class);
                // show the properties of this individual
                //System.out.println("  " + ind.getURI().toString().replace("http://www.w3.org/ns/prov#", ""));
                ontologys.add(ind.getURI().toString().replace(baseURI, ""));

                for (StmtIterator j = ind.listProperties(); j.hasNext();) {
                    Statement s = j.next();
                    //System.out.print("    " + s.getPredicate().getLocalName().toString().replace("http://www.w3.org/ns/prov#", "") + " -> ");
                    temp1 = (s.getPredicate().getLocalName().toString().replace(baseURI, "") + " -> ");
                    if (s.getObject().isLiteral()) {
                        //System.out.println(s.getLiteral().getLexicalForm().replace("http://www.w3.org/ns/prov#", ""));
                        ontologys.add(s.getLiteral().getLexicalForm().replace(baseURI, ""));
                    } else {
                        //System.out.println(s.getObject().toString().replace("http://www.w3.org/ns/prov#", ""));
                        temp2 = s.getObject().toString().replace(baseURI, "");
                        ontologys.add(temp1 + temp2);
                    }
                }
            }
        }
        return ontologys;
    } */
    public List<String> buscarSPARQL(String sql) {
        //variavel global
        OntModel model;
        //uri da ontology
        String baseURI = "http://www.w3.org/ns/prov#";

        //inicia a maquina de inferencia e carrega a ontology nela
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.read(loadOntology);
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
        reasoner = reasoner.bindSchema(ontModel);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;
        ontModelSpec.setReasoner(reasoner);
        //ontologia carregada na máquina de inferencia
        model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);

        if (sql.equals("")) {
            sql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                    + "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
                    + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                    + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
                    + "PREFIX prov: <http://www.w3.org/ns/prov#>\n"
                    + "\n"
                    + "SELECT ?subject ?object\n"
                    + "	WHERE { ?subject prov:Used <http://www.w3.org/ns/prov#Sum>}";
        }
        Query query = QueryFactory.create(sql);
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();
        List resultslist = new ArrayList();
        while (results.hasNext()) {
            QuerySolution next = results.next();
            String result = null;
            result = next.toString().replace("( ?subject = <http://www.w3.org/ns/prov#", "");
            resultslist.add(result.replace("> )", ""));
        }

        return resultslist;
    }

    public List<String> buscarEvolutionTo(String workflow) {
        //variavel global
        OntModel model;
        //uri da ontology
        String baseURI = "http://www.w3.org/ns/prov#";

        //inicia a maquina de inferencia e carrega a ontology nela
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.read(loadOntology);
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
        reasoner = reasoner.bindSchema(ontModel);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;
        ontModelSpec.setReasoner(reasoner);
        //ontologia carregada na máquina de inferencia
        model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);
        String sql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
                + "PREFIX prov: <http://www.w3.org/ns/prov#>\n"
                + "\n"
                + "SELECT ?subject ?object\n"
                + "	WHERE { ?subject prov:EvolutionOf prov:" + workflow.replace(" ", ".") + ".}";
        Query query = QueryFactory.create(sql);
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();
        List resultslist = new ArrayList();
        while (results.hasNext()) {
            QuerySolution next = results.next();
            String result = null;
            result = next.toString().replace("( ?subject = <http://www.w3.org/ns/prov#", "");
            resultslist.add(result.replace("> )", ""));
        }

        return resultslist;
    }

    public List<String> buscarEvolutionOf(String workflow) {
        //variavel global
        OntModel model;
        //uri da ontology
        String baseURI = "http://www.w3.org/ns/prov#";

        //inicia a maquina de inferencia e carrega a ontology nela
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.read(loadOntology);
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
        reasoner = reasoner.bindSchema(ontModel);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;
        ontModelSpec.setReasoner(reasoner);
        //ontologia carregada na máquina de inferencia
        model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);
        String sql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
                + "PREFIX prov: <http://www.w3.org/ns/prov#>\n"
                + "\n"
                + "SELECT ?subject ?object\n"
                + "	WHERE { ?subject prov:EvolutionTo prov:" + workflow.replace(" ", ".") + ".}";
        Query query = QueryFactory.create(sql);
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();
        List resultslist = new ArrayList();
        while (results.hasNext()) {
            QuerySolution next = results.next();
            String result = null;
            result = next.toString().replace("( ?subject = <http://www.w3.org/ns/prov#", "");
            resultslist.add(result.replace("> )", ""));
        }

        return resultslist;
    }

}
