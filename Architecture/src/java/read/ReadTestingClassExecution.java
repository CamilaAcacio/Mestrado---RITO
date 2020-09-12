/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import dao.TestingClassDAO;
import dao.TestingSuiteDAO;
import dao.TestingSuiteExecutionDAO;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import model.Testingclass;
import model.Testingclassexecution;
import model.Testingsuiteexecution;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import read.LerIdBuild;

/**
 *
 * @author Camila
 */
public class ReadTestingClassExecution {

    public ReadTestingClassExecution() {
    }

    public List<Testingclassexecution> GetReadTestingClass(String build, String log, String projectLog, String idBuildProject) {

        List<Testingclassexecution> listTestingClassExecution = new ArrayList<Testingclassexecution>();

        try {
            File fXmlFile = new File(log);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            List<Testingsuiteexecution> listTestingSuiteExe = new ArrayList<>();
            listTestingSuiteExe = TestingSuiteExecutionDAO.obterTestingSuiteexecutionPerId(idBuildProject);
            List<Testingclass> listTestingClass = new ArrayList<>();

            NodeList listOfSuite = doc.getElementsByTagName("suite");

            for (int temp = 0; temp < listOfSuite.getLength(); temp++) {
                Node nNode = listOfSuite.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String name = (eElement.getElementsByTagName("name").item(0).getTextContent());
                    float duration = (Float.valueOf(eElement.getElementsByTagName("duration").item(0).getTextContent()));
                    listTestingClass = TestingClassDAO.obterTestingclassPerName(idBuildProject, name);

                    Testingclassexecution testingClassExecution = new Testingclassexecution(listTestingClass.get(0), listTestingSuiteExe.get(0), duration);
                    listTestingClassExecution.add(testingClassExecution);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTestingClassExecution;

    }

}
