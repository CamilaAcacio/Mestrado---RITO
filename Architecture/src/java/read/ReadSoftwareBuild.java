/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import dao.SoftwareArtifactDAO;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import model.Softwareartifact;
import model.Softwarebuild;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Camila
 */
public class ReadSoftwareBuild {

    public ReadSoftwareBuild() {
    }

    public Softwarebuild GetSoftwareBuild(String build, String projectLog, String idBuildProject) throws ClassNotFoundException, SQLException {

        Softwarebuild softwareBuild = new Softwarebuild();
        double duration = 0;
        String startTime = null;
        List<Softwareartifact> software = new ArrayList<>();
        software = SoftwareArtifactDAO.obterSoftwareartifactPerId(idBuildProject);

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
                    duration = (Double.parseDouble(eElement.getElementsByTagName("duration").item(0).getTextContent()));
                    startTime = (eElement.getElementsByTagName("startTime").item(0).getTextContent());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        softwareBuild = new Softwarebuild(idBuildProject, software.get(0), startTime, duration);
        return softwareBuild;

    }

}
