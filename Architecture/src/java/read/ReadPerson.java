/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import dao.AgentDAO;
import model.Person;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import model.Agent;

/**
 *
 * @author Camila
 */
public class ReadPerson {

    public ReadPerson() {

    }

    public Person GetPerson(String build, String id) {
        Person person = new Person();
        List<Agent> agent = new ArrayList<>();
        try {
            File fXmlFile = new File(build);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            
            agent = AgentDAO.obterAgent(id);
            person.setIdperson(id);
            person.setAgent(agent.get(0));
            
            NodeList listOfSuite = doc.getElementsByTagName("build");
            NodeList listOfNames = doc.getElementsByTagName("userId");

            if (listOfNames.getLength() == 1) {
                for (int temp = 0; temp < listOfSuite.getLength(); temp++) {
                    Node nNode = listOfSuite.item(temp);

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        person.setName(eElement.getElementsByTagName("userId").item(0).getTextContent());
                    }

                }
            } else {
                String name = "Gerado Automatimente";
                person.setName(name);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
       // person = new Person(id, agent.get(0));
        return person;
    }
}
