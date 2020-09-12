/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import dao.ActivityDAO;
import model.Commitactivity;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Activity;

/**
 *
 * @author Camila
 */
public class ReadCommit {

    public ReadCommit() {
    }

    public Commitactivity GetCommit(String build, String change, String idBuildProject) throws ClassNotFoundException, SQLException {

        Commitactivity commitingActivity = new Commitactivity();
        commitingActivity.setIdcommitActivity(idBuildProject);
        List<Activity> listActivity = new ArrayList<>();
        listActivity = ActivityDAO.obterActivityPerId(idBuildProject);
        commitingActivity.setActivity(listActivity.get(0));
        int i = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(change))) {

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                if (sCurrentLine.contains("commit") && i == 0) {
                    String commit = sCurrentLine;
                    String splited[] = commit.split("\\s+");
                    commitingActivity.setCommit(splited[1]);
                    i = 1;
                } else {
                    if (sCurrentLine.contains("tree")) {
                        String tree = sCurrentLine;
                        String splited[] = tree.split("\\s+");
                        commitingActivity.setTree(splited[1]);
                    } else {
                        if (sCurrentLine.contains("parent")) {
                            String parent = sCurrentLine;
                            String splited[] = parent.split("\\s+");
                            commitingActivity.setParent(splited[1]);
                        } else {
                            if (sCurrentLine.contains("author")) {
                                String author = sCurrentLine;
                                String splited[] = author.split("\\s+");
                                commitingActivity.setAuthor(splited[1]);
                                commitingActivity.setDate(splited[3]);
                                commitingActivity.setHour(splited[4]);
                            } else {
                                if (sCurrentLine.contains("committer") && i == 1) {
                                    String commiter = sCurrentLine;
                                    String splited[] = commiter.split("\\s+");
                                    commitingActivity.setCommiter(splited[1]);
                                } else {

                                }

                            }
                        }
                    }

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return commitingActivity;
    }

}
