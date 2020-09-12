/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import dao.ProjectDAO;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import model.Project;
import model.Softwareartifact;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Camila
 */
public class ReadSoftwareArtifact {

    public Softwareartifact getSoftwareartifact(String Build, String projectLog, String idBuildProject) throws ClassNotFoundException, SQLException {
        List<Project> listProject = new ArrayList();
        listProject = ProjectDAO.obterTProjectPerId(idBuildProject);
        Softwareartifact software = new Softwareartifact(idBuildProject, listProject.get(0));

        return software;
    }

}
