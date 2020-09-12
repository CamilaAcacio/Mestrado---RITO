/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import dao.ProjectDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import model.Testingsuite;

/**
 *
 * @author Camila
 */
public class ReadTestingSuite {

    public ReadTestingSuite() {
    }

    public Testingsuite GetTestingSuite(String build, String projectLog, String idBuildProject) throws ClassNotFoundException, SQLException {
        List<Project> project = new ArrayList<>();
        project = ProjectDAO.obterTProjectPerId(idBuildProject);

        Testingsuite testingsuite = new Testingsuite(idBuildProject, project.get(0));
    
        return testingsuite;
    }

}
