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
public class TestingSuiteExecutionDAO {

    public static List<Testingsuiteexecution> obterTestingsuiteexecution() throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `testingsuiteexecution`";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Testingsuiteexecution.class);
        List testingsuiteexe = query.list();
        session.close();
        return testingsuiteexe;
    }

    
     public static List<Testingsuiteexecution> obterTestingSuiteexecutionPerId(String idProject) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `testingsuiteexecution` WHERE `idtestingSuiteexecution` = '"+idProject+ "'";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Testingsuiteexecution.class);
        List testingsuiteexe = query.list();
        session.close();
        return testingsuiteexe;
    }
   
    public static void gravarTestingsuiteexecution(Testingsuiteexecution testingSuiteExecution) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.save(testingSuiteExecution);
        transaction.commit();
    }

    public static void editarTestingsuiteexecution(Testingsuiteexecution testingSuiteExecution) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(testingSuiteExecution);
        transaction.commit();
    }

    public static void excluirTestingsuiteexecution(Testingsuiteexecution testingSuiteExecution) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(testingSuiteExecution);
        transaction.commit();
    }

}
