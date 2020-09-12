/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import dao.TestingSourceCodeDAO;
import dao.TestingSuiteDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Composedof;
import model.Testingsourcecode;
import model.Testingsuite;

/**
 *
 * @author Camila
 */
class ReadComposedOf {

    public ReadComposedOf() {
    }

    public List<Composedof> createComposedOf(String buildNumber) throws ClassNotFoundException, SQLException {
        List<Composedof> listComposed = new ArrayList<>();
        Composedof composedOf = new Composedof();

        List<Testingsourcecode> testingSourceCode = new ArrayList<>();
        testingSourceCode = TestingSourceCodeDAO.obterTestingsourcecodePorArtifact(buildNumber);
        for (Testingsourcecode a : testingSourceCode) {
            List<Testingsuite> testingSuite = new ArrayList<>();
            testingSuite = TestingSuiteDAO.obterTestingSuitePerId(buildNumber);
            composedOf = new Composedof(a, testingSuite.get(0));
            listComposed.add(composedOf);
        }

        return listComposed;
    }

}
