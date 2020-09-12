/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import dao.ActivityDAO;
import dao.EnvironmentDAO;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import model.Activity;
import model.Hasenvironment;
import model.Buildexecution;
import model.Environment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Camila
 */
public class ReadBuildExecution {

    public ReadBuildExecution() {
    }

    public Buildexecution getBuildExecution(String build, String idBuildProject) {
        Buildexecution buildExecution = new Buildexecution();
        try {
            File fXmlFile = new File(build);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            List<Activity> listActivity = new ArrayList<>();
            

            NodeList listOfSuite = doc.getElementsByTagName("build");

            for (int temp = 0; temp < listOfSuite.getLength(); temp++) {
                Node nNode = listOfSuite.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    //build execution
                    //int id = ((Integer.valueOf(eElement.getElementsByTagName("hudsonBuildNumber").item(0).getTextContent())));
                    String result = (String.valueOf(eElement.getElementsByTagName("result").item(0).getTextContent()));
                    int duration = (Integer.valueOf(eElement.getElementsByTagName("duration").item(0).getTextContent()));
                    String workspace = ((String.valueOf(eElement.getElementsByTagName("workspace").item(0).getTextContent())));
                    listActivity = ActivityDAO.obterActivityPerId(idBuildProject);
                    buildExecution = new Buildexecution(idBuildProject, listActivity.get(0), result, duration, workspace);
                    

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buildExecution;
    }

}
