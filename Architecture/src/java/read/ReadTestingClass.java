/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import dao.TestingSuiteDAO;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import model.Testingclass;
import model.Testingclassexecution;
import model.Testingsuite;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Camila
 */
public class ReadTestingClass {

    public ReadTestingClass() {
    }

    public List<Testingclass> GetTestingClass(String build, String log, String projectLog, String idBuildProject) {

        List<Testingclass> listTestingClass = new ArrayList<Testingclass>();

        try {
            File fXmlFile = new File(log);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            //  LerIdBuild idBuild = new LerIdBuild();
            List<Testingsuite> testingSuite = new ArrayList<>();
            testingSuite = TestingSuiteDAO.obterTestingSuitePerId(idBuildProject);

            NodeList listOfSuite = doc.getElementsByTagName("suite");

            for (int temp = 0; temp < listOfSuite.getLength(); temp++) {
                Node nNode = listOfSuite.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element caseElement = (Element) nNode;

                        //Elementos TestingClass
                        String className = caseElement.getElementsByTagName("className").item(0).getTextContent();

                        Testingclass testingClass = new Testingclass(testingSuite.get(0), className);
                        listTestingClass.add(testingClass);

                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listTestingClass;
    }

}
