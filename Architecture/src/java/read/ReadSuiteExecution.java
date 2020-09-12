/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import dao.ActivityDAO;
import dao.TestingLogDAO;
import dao.TestingSuiteDAO;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import model.Activity;
import model.Buildexecution;
import model.Testinglog;
import model.Testingsuite;
import model.Testingsuiteexecution;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Camila
 */
public class ReadSuiteExecution {

    public ReadSuiteExecution() {
    }

    public Testingsuiteexecution GetTestingSuiteExecution(String build, String log, String projectLog, String idBuildProject) throws ClassNotFoundException, SQLException {

        Testingsuiteexecution testingSuiteExecution = new Testingsuiteexecution();
        int id = 0;
        float duration = 0;
        int fail = 0;
        int skipped = 0;
        int total = 0;
        double scale = 0;
        int successful = 0;

        List<Testinglog> testingLog = new ArrayList<>();
        testingLog = TestingLogDAO.obterTestinglogPorId(idBuildProject);
        List<Testingsuite> listTestingSuite = new ArrayList<>();
        listTestingSuite = TestingSuiteDAO.obterTestingSuitePerId(idBuildProject);
        List<Activity> listActivity = new ArrayList<>();
        listActivity = ActivityDAO.obterActivityPerId(idBuildProject);

        try {
            File fXmlFile = new File(build);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList listOfSuite = doc.getElementsByTagName("build");

            for (int temp = 0; temp < listOfSuite.getLength(); temp++) {
                Node nNode = listOfSuite.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    //build execution
                    id = (Integer.valueOf(eElement.getElementsByTagName("hudsonBuildNumber").item(0).getTextContent()));
                    duration = Float.valueOf(testingLog.get(0).getGenerationDateTime());
                    fail = (Integer.valueOf(eElement.getElementsByTagName("failCount").item(0).getTextContent()));
                    skipped = (Integer.valueOf(eElement.getElementsByTagName("skipCount").item(0).getTextContent()));
                    total = (Integer.valueOf(eElement.getElementsByTagName("totalCount").item(0).getTextContent()));
                    scale = (Double.valueOf(eElement.getElementsByTagName("healthScaleFactor").item(0).getTextContent()));
                    successful = (total - fail - skipped);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        testingSuiteExecution = new Testingsuiteexecution(idBuildProject, listActivity.get(0), listTestingSuite.get(0), duration, successful, fail, skipped, total, scale, id);
        return testingSuiteExecution;
    }

}
