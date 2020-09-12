/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import dao.ProjectDAO;
import java.sql.SQLException;
import model.Testingartifact;
import model.Project;
import java.util.ArrayList;
import java.util.List;
import read.LerProjectName;

/**
 *
 * @author Camila
 */
public class ReadTestingArtifact {

    public ReadTestingArtifact() {
    }

    public Testingartifact getTestingArtifact(String Build, String projectLog, String idBuildProject) throws ClassNotFoundException, SQLException {
        List<Project> listProject = new ArrayList();  
        listProject = ProjectDAO.obterTProjectPerId(idBuildProject);
        Testingartifact testing = new Testingartifact(idBuildProject, listProject.get(0));

        return testing;

    }

}
