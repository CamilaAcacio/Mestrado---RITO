/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import dao.EntityDAO;
import model.Project;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import model.Entityprov;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Camila
 */
public class ReadProject {

    public ReadProject() {
    }

    public Project GetProject(String build, String projectLog, String id) {
         Project project = new Project();

        try {
            File fXmlFile = new File(projectLog);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            
            List<Entityprov> entity = new ArrayList<>();
            entity = EntityDAO.obterEntity(id);

           

            NodeList listOfSuite = doc.getElementsByTagName("project");

            for (int temp = 0; temp < listOfSuite.getLength(); temp++) {
                Node nNode = listOfSuite.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    project.setIdproject(id);
                    project.setName(eElement.getElementsByTagName("artifactId").item(0).getTextContent());
                    project.setProjectVersion(eElement.getElementsByTagName("version").item(0).getTextContent());
                    project.setEntityprov(entity.get(0));
                  
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return project;
    }

}
