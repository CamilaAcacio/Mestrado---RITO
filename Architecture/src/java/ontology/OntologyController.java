/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontology;


import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.Reasoner;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Iterator;
import org.mindswap.pellet.jena.PelletReasonerFactory;

/**
 *
 * @author Camila
 */
public class OntologyController {

    //variavel para acesso à ontologia com inferencia
    private final InfModel infModel;

    //variavel para acesso à ontologia sem inferencia
    private final OntModel ontModel;

    //uri da ontologia
    public static final String URI = "http://www.w3.org/ns/prov-o-test#";

    //caminho para a ontologia
    public static final String ONTOLOGY = "file:///Users/Camila/Dropbox/Architecture/src/java/resource/prov-o-test.rdf";

    //caminho para a ontologia carregada
    public static final String ONTOLOGY_LOAD = "C:/Users/Camila/Dropbox/Architecture/src/java/resource/prov-o-test-load.rdf";

    private static OntologyController instance;

    public static OntologyController getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new OntologyController();
        }
        return instance;
    }

    private OntologyController() throws SQLException, ClassNotFoundException {
        DataHandler.loadDAO();

        Model model = ModelFactory.createDefaultModel();

        model.read(Paths.get(ONTOLOGY_LOAD).toUri().toString(), "RDF/XML"); //

        ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, model);
        ontModel.loadImports();
        ontModel.prepare();

        Reasoner reasoner = PelletReasonerFactory.theInstance().create();
        infModel = ModelFactory.createInfModel(reasoner, ontModel);
        infModel.prepare();
    }

    public InfModel getInfModel() {
        return infModel;
    }

    public OntModel getOntModel() {
        return ontModel;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //colocar public static final String ONTOLOGY = "/files/ontologies/prov-se-o.owl";
        //e Paths.get(OntologyController.ONTOLOGY).toUri().toString() no read
        //try {
            OntologyController ontologyController = new OntologyController();
            Model model = ontologyController.getInfModel();
            
            
            String queryString = "PREFIX ont: <http://www.w3.org/ns/prov-o-test#> \n"
                    + "SELECT * \n"
                    + "WHERE { ?activity ont:activityId ?ID. \n"
                    + "} ";
            
            Query query = QueryFactory.create(queryString);
            QueryExecution qexec = QueryExecutionFactory.create(query, model);
                Iterator<QuerySolution> rs = qexec.execSelect();
                for (; rs.hasNext();) {
                    QuerySolution soln = rs.next();
                    System.out.println(soln.toString());
                }
                System.out.println("OK \\o/ \\o/");
            } 
           
          /*  InferenceLayer inferenceLayer = new InferenceLayer();
            List<String> jenaGetEvolutionTo = inferenceLayer.sparqlGetIndividualsByClassInf("<http://www.w3.org/ns/prov-o-test#>", "Activity");
            for (String string : jenaGetEvolutionTo) {
                System.out.println(string);
            } */
            
      //  } catch (ClassNotFoundException e) {
      //      throw e;
        }
