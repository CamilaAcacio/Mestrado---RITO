/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import dao.TestingClassDAO;
import dao.TestingClassExecutionDAO;
import dao.TestingMethodDAO;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import model.Testingclass;
import model.Testingclassexecution;
import model.Testingmethod;
import model.Testingmethodexecution;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Camila
 */
public class ReadTestingMethodExecution {

    public ReadTestingMethodExecution() {
    }

    public List<Testingmethodexecution> GetTestingMethod(String build, String log, String projectLog, String idBuildProject) {

        List<Testingmethodexecution> listTestingMethodExe = new ArrayList<>();

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

                    String name = eElement.getElementsByTagName("name").item(0).getTextContent();
                    //busca a classe de teste
                    List<Testingclass> listTestingClass = new ArrayList<>();
                    listTestingClass.clear();
                    listTestingClass = TestingClassDAO.obterTestingclassPerName(idBuildProject, name);
                    //busca a execução daquela classe de teste
                    List<Testingclassexecution> listTestingClassExecution = new ArrayList<>();
                    listTestingClassExecution.clear();
                    listTestingClassExecution = TestingClassExecutionDAO.obterTestingClassExePerId(listTestingClass.get(0).getIdtestingClass());

                    NodeList listOfSubCases = eElement.getElementsByTagName("case");

                    for (int i = 0; i < listOfSubCases.getLength(); i++) {
                        Node caseNode = listOfSubCases.item(i);

                        if (caseNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element caseElement = (Element) caseNode;
                            
                            String nameMethod = caseElement.getElementsByTagName("testName").item(0).getTextContent();

                            List<Testingmethod> listTestingMethod = new ArrayList<>();
                            listTestingMethod.clear();
                            listTestingMethod = TestingMethodDAO.obterTestingmethodPerName(listTestingClass.get(0).getIdtestingClass(), nameMethod);

                            //Elementos TestingClass
                            float time = Float.valueOf(caseElement.getElementsByTagName("duration").item(0).getTextContent());
                            String skipped = caseElement.getElementsByTagName("skipped").item(0).getTextContent();
                            int fail = (Integer.valueOf(caseElement.getElementsByTagName("failedSince").item(0).getTextContent()));
                            String errorStrackTrace;
                            String errorDetails;
                            String test = "true";
                            String result = null;

                            //caso de falha em algum caso de teste
                            if (fail != 0) {
                                errorStrackTrace = (caseElement.getElementsByTagName("errorStackTrace").item(0).getTextContent());
                                errorDetails = (caseElement.getElementsByTagName("errorDetails").item(0).getTextContent());
                                result = "fail";
                            } else {
                                if (skipped.equals(test)) {
                                    result = "skipped";
                                    errorStrackTrace = null;
                                    errorDetails = null;
                                } else {
                                    result = "passed";
                                    errorStrackTrace = null;
                                    errorDetails = null;
                                }

                            }

                            Testingmethodexecution testingMethodExecution = new Testingmethodexecution(listTestingClassExecution.get(0), listTestingMethod.get(0), time, result, errorDetails, errorStrackTrace);
                            listTestingMethodExe.add(testingMethodExecution);
                        }

                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listTestingMethodExe;
    }

}
