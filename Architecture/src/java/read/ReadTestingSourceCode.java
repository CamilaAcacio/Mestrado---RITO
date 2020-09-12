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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import model.Project;
import model.Testingartifact;
import model.Testingclass;
import model.Testingsourcecode;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Camila
 */
public class ReadTestingSourceCode {

    public ReadTestingSourceCode() {
    }

    public List<Testingsourcecode> GetTestingSourceCode(String build, String log, String projectLog, String idBuildProject) {

        List<Testingsourcecode> listTestingSourceCode = new ArrayList<Testingsourcecode>();

        try {
            File fXmlFile = new File(log);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
           
            List<Testingartifact> testing = new ArrayList<>();
            testing = TestingArtifactDAO.obterTestingartifactPerId(idBuildProject);
            
            
            NodeList listOfSuite = doc.getElementsByTagName("suite");

            for (int temp = 0; temp < listOfSuite.getLength(); temp++) {
                Node nNode = listOfSuite.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String path = eElement.getElementsByTagName("file").item(0).getTextContent();
                    NodeList listOfSubCases = eElement.getElementsByTagName("case");

                    for (int i = 0; i < listOfSubCases.getLength(); i++) {
                        Node caseNode = listOfSubCases.item(i);

                        if (caseNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element caseElement = (Element) caseNode;

                            String nameTest = caseElement.getElementsByTagName("testName").item(0).getTextContent();
                            Testingsourcecode testingSourceCode = new Testingsourcecode(testing.get(0), nameTest, path);
                            listTestingSourceCode.add(testingSourceCode);
                        }

                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listTestingSourceCode;
    }

}
