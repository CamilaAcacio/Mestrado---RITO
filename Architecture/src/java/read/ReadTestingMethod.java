/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import dao.TestingClassDAO;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import model.Testingclass;
import model.Testingmethod;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Camila
 */
public class ReadTestingMethod {

    public ReadTestingMethod() {
    }

    public List<Testingmethod> GetTestingMethod(String build, String log, String projectLog, String idBuildProject) {

        List<Testingmethod> listTestingMethod = new ArrayList<Testingmethod>();

        try {
            File fXmlFile = new File(log);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList listOfSuite = doc.getElementsByTagName("suite");

            for (int temp = 0; temp < listOfSuite.getLength(); temp++) {
                Node nNode = listOfSuite.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    
                    String className = null; 
                    className = eElement.getElementsByTagName("name").item(0).getTextContent();
                    List<Testingclass> listTestingClass = new ArrayList<>();
                    listTestingClass = TestingClassDAO.obterTestingclassPerName(idBuildProject, className);

                    NodeList listOfSubCases = eElement.getElementsByTagName("case");

                    for (int i = 0; i < listOfSubCases.getLength(); i++) {
                        Node caseNode = listOfSubCases.item(i);

                        if (caseNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element caseElement = (Element) caseNode;
                      
                            String testName = caseElement.getElementsByTagName("testName").item(0).getTextContent();
                            String skipped = caseElement.getElementsByTagName("skipped").item(0).getTextContent();

                            Testingmethod testingMethod = new Testingmethod(listTestingClass.get(0), testName, skipped);
                            listTestingMethod.add(testingMethod);
                        }

                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listTestingMethod;
    }

}
