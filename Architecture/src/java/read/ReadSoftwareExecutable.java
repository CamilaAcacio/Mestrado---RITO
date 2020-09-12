/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import dao.ProjectDAO;
import dao.SoftwareArtifactDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import model.Softwareartifact;
import model.Softwareexecutable;

/**
 *
 * @author Camila
 */
public class ReadSoftwareExecutable {

    public ReadSoftwareExecutable() {
    }

    public Softwareexecutable GetSoftwareExecutable(String build, String projectLog, String idProjectLog) throws ClassNotFoundException, SQLException {
        List<Project> listProject = new ArrayList<>();
        listProject = ProjectDAO.obterTProjectPerId(idProjectLog);
        String name = listProject.get(0).getName();
        listProject = ProjectDAO.obterTProjectPerId(idProjectLog);
        List<Softwareartifact> software = new ArrayList<>();
        software = SoftwareArtifactDAO.obterSoftwareartifactPerId(idProjectLog);
        
        Softwareexecutable softwareExecutable = new Softwareexecutable(idProjectLog, software.get(0), name);

        return softwareExecutable;
    }

}
