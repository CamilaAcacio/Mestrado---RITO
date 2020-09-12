/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import model.Softwareartifact;
import model.Sourcecode;
import model.StructCoversSourceCode;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import traceability.Traceability;

/**
 *
 * @author Camila
 */
public class ReadSourceCode {

    public ReadSourceCode() {
    }

    public List<StructCoversSourceCode> GetSourceCode(String build, String log, String projectLog, List<String> testPath) {

        List<StructCoversSourceCode> listSourceCode = new ArrayList<>();
        String name;

        try {
            File fXmlFile = new File(log);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            ReadProject project = new ReadProject();
            Softwareartifact software = new Softwareartifact();

            NodeList listOfSuite = doc.getElementsByTagName("suite");

            for (int temp = 0; temp < listOfSuite.getLength(); temp++) {
                Node nNode = listOfSuite.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    NodeList listOfSubCases = eElement.getElementsByTagName("case");

                    for (int i = 0; i < listOfSubCases.getLength(); i++) {
                        Node caseNode = listOfSubCases.item(i);

                        if (caseNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element caseElement = (Element) caseNode;

                            String path = null;
                            String teste = "test";
                            String nameComplet = caseElement.getElementsByTagName("testName").item(0).getTextContent();
                            if (nameComplet.contains(teste)) {
                                name = nameComplet.replaceAll("test", "");
                                name = nameComplet.replaceAll("Test", "");
                                Sourcecode sourceCode = new Sourcecode(software, name, path);
                                StructCoversSourceCode structCoversSourceCode = new StructCoversSourceCode(name, sourceCode);
                                listSourceCode.add(structCoversSourceCode);
                                

                            } else {
                                Traceability trace = new Traceability();
                                List<String> listTestPath = trace.buscarSub(testPath);
                                List<String> listCode = new ArrayList<>();
                                listCode = trace.traceability(nameComplet, listTestPath);
                                for (String a : listCode){
                                    Sourcecode sourceCode = new Sourcecode(software, a, path);
                                    StructCoversSourceCode structCoversSourceCode = new StructCoversSourceCode(nameComplet, sourceCode);
                                    listSourceCode.add(structCoversSourceCode);
                                }                              
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSourceCode;
    }
}
