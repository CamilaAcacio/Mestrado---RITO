/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Testingclassexecution;
import model.Testingmethod;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author Camila
 */
public class TestingClassExecutionDAO {

    public static List<Testingclassexecution> obterTestingclassexecution() throws ClassNotFoundException, SQLException {
         Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `testingclassexecution`";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Testingclassexecution.class);
        List testingclassexe = query.list();
        session.close();
        return testingclassexe;
    }

    public static List<Testingclassexecution> obterTestingClassExePerId(int idTestingClass) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `testingclassexecution` WHERE `testingClass_idtestingClass` = "+idTestingClass+ "";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Testingclassexecution.class);
        List testingclassexe = query.list();
        session.close();
        return testingclassexe;
    }
    
    public static List<Testingclassexecution> obterTestingClassExePerProject(String idTestingClass) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `testingclassexecution` WHERE `testingSuiteExecution_idtestingSuiteExecution` = '"+idTestingClass+ "'";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Testingclassexecution.class);
        List testingclassexe = query.list();
        session.close();
        return testingclassexe;
    }
    
     public static List<Testingclassexecution> obterTestingClassExePerSuite(String idTestingClass) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `testingclassexecution` WHERE `testingSuiteExecution_idtestingSuiteExecution` = '"+idTestingClass+ "'";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Testingclassexecution.class);
        List testingclassexe = query.list();
        session.close();
        return testingclassexe;
    }

    public static void gravarTestingclassexecution(Testingclassexecution testingClassExecution) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.save(testingClassExecution);
        transaction.commit();
    }

    public static void editarTestingclassexecution(Testingclassexecution testingClassExecution) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(testingClassExecution);
        transaction.commit();
    }

    public static void excluirTestingclassexecution(Testingclassexecution testingClassExecution) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(testingClassExecution);
        transaction.commit();
    }

}
