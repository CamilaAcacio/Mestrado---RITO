/*
 * To change this license header, choose License Headers in Testingmethodexecution Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Testingmethodexecution;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author Camila
 */
public class TestingMethodExecutionDAO {

    public static List<Testingmethodexecution> obterTestingmethodexecution() throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `testingmethodexecution`";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Testingmethodexecution.class);
        List testingmethodexecution = query.list();
        session.close();
        return testingmethodexecution;
    }

    public static List<Testingmethodexecution> obterTestingmethodexecutionPorClass(int idProject) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `testingmethodexecution` WHERE `testingClassExecution_idtestingClassExecution` = "+idProject+ "";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Testingmethodexecution.class);
        List testingmethodexecution = query.list();
        session.close();
        return testingmethodexecution;
    }

    public static Testingmethodexecution obterTestingmethodexecution(int idTestingmethodexecution) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Testingmethodexecution testingMethodExecution = (Testingmethodexecution) session.load(Testingmethodexecution.class, idTestingmethodexecution);

        return testingMethodExecution;
    }

    public static void gravarTestingmethodexecution(Testingmethodexecution testingMethodExecution) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.save(testingMethodExecution);
        transaction.commit();
    }

    public static void editarTestingmethodexecution(Testingmethodexecution testingMethodExecution) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(testingMethodExecution);
        transaction.commit();
    }

    public static void excluirTestingmethodexecution(Testingmethodexecution testingMethodExecution) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(testingMethodExecution);
        transaction.commit();
    }

}
