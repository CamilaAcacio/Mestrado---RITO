/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import dao.ProjectDAO;
import dao.TestingArtifactDAO;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import model.Project;
import model.Testingartifact;
import model.Testinglog;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Camila
 */
public class ReadTestingLog {

    public ReadTestingLog() {
    }

    public Testinglog GetTestingLog(String build, String log, String projectLog, String idBuildProject) {

        Testinglog testingLog = new Testinglog();

        try {
            File fXmlFile = new File(log);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            List<Testingartifact> testing = new ArrayList<>();
            testing = TestingArtifactDAO.obterTestingartifactPerId(idBuildProject);

            NodeList listOfSuite = doc.getElementsByTagName("result");

            for (int temp = 0; temp < listOfSuite.getLength(); temp++) {
                Node nNode = listOfSuite.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String duration = ((eElement.getElementsByTagName("duration").item(0).getTextContent()));
                    String path = eElement.getElementsByTagName("file").item(0).getTextContent();
                    String splited[] = path.split(Pattern.quote("workspace"));

                    testingLog = new Testinglog(idBuildProject, testing.get(0), splited[0], duration);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return testingLog;
    }

}
