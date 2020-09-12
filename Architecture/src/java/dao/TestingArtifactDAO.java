/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Testingartifact;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author Camila
 */
public class TestingArtifactDAO {
    

    public static List<Testingartifact> obterTestingartifact() throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `testingArtifact`";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Testingartifact.class);
        List testingArtifact = query.list();
        session.close();
        return testingArtifact;
    }
    
    public static List<Testingartifact> obterTestingartifactPerId(String idProject) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `testingArtifact` WHERE `idtestingArtifact` = '"+idProject+ "'";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Testingartifact.class);
        List testingArtifact = query.list();
        session.close();
        return testingArtifact;
    }

    public static List<Testingartifact> obterTestingartifact(int idTestingartifact) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `testingArtifact` WHERE `idtestingArtifact` = "+idTestingartifact+ "";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Testingartifact.class);
        List testingArtifact = query.list();
        session.close();
        return testingArtifact;
    }

    public static void gravarTestingartifact(Testingartifact testingArtifact) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.save(testingArtifact);
        transaction.commit();

    }

    public static void editarTestingartifact(Testingartifact testingArtifact) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(testingArtifact);
        transaction.commit();
    }

    public static void excluirTestingartifact(Testingartifact testingArtifact) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(testingArtifact);
        transaction.commit();
    }

}

