/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Testinglog;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author Camila
 */
public class TestingLogDAO {

    public static List<Testinglog> obterTestinglog() throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `testinglog`";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Testinglog.class);
        List testingLogs = query.list();
        session.close();
        return testingLogs;
    }

    public static List<Testinglog> obterTestinglogPorId(String id) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `testinglog` WHERE `idtestinglog` = '"+id+ "'";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Testinglog.class);
        List testingLogs = query.list();
        session.close();
        return testingLogs;
    }

    public static void gravarTestinglog(Testinglog testingLog) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.save(testingLog);
        transaction.commit();
    }

    public static void editarTestinglog(Testinglog testingLog) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(testingLog);
        transaction.commit();
    }

    public static void excluirTestinglog(Testinglog testingLog) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(testingLog);
        transaction.commit();
    }

}
