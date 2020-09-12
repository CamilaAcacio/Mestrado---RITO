/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Testingclassexecution;
import model.Testingsuite;
import model.Testingsuiteexecution;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author Camila
 */
public class TestingSuiteDAO {

    public static List<Testingsuite> obterTestingsuite() throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `testingsuite`";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Testingsuite.class);
        List testingsuite = query.list();
        session.close();
        return testingsuite;
    }

     public static List<Testingsuite> obterTestingSuitePerId(String idProject) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `testingsuite` WHERE `idtestingSuite` = '"+idProject+ "'";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Testingsuite.class);
        List testingsuite = query.list();
        session.close();
        return testingsuite;
    }
     
    public static Testingsuite obterTestingsuite(int idTestingsuite) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Testingsuite testingSuite = (Testingsuite) session.load(Testingsuite.class, idTestingsuite);

        return testingSuite;
    }

    public static void gravarTestingsuite(Testingsuite testingSuite) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.save(testingSuite);
        transaction.commit();
    }

    public static void editarTestingsuite(Testingsuite testingSuite) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(testingSuite);
        transaction.commit();
    }

    public static void excluirTestingsuite(Testingsuite testingSuite) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(testingSuite);
        transaction.commit();
    }

}
