/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import read.LerProjectName;

/**
 *
 * @author Camila
 */
public class LerIdBuild {

    public LerIdBuild() {
    }

    public String ReadIdBuild(String build, String projectLog) {
        String id = null;
        String name = null;
        String idBuild = null;
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

                    idBuild = eElement.getElementsByTagName("hudsonBuildNumber").item(0).getTextContent();

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        LerProjectName projecName = new LerProjectName();
        name = projecName.ReadNameProject(projectLog);
        StringBuilder strBuf = new StringBuilder();
        strBuf.append(idBuild);
        strBuf.append(".");
        strBuf.append(name);
        id = strBuf.toString();

        return id;
    }

}
