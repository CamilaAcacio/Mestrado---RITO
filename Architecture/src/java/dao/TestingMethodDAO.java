/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Testingclass;
import model.Testingmethod;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author Camila
 */
public class TestingMethodDAO {

    public static List<Testingmethod> obterTestingmethod() throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `testingmethod`";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Testingmethod.class);
        List testingMethod = query.list();
        session.close();
        return testingMethod;
    }

    public static List<Testingmethod> obterTestingmethodPorId(int id) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `testingmethod` WHERE `idtestingMethod` = " + id +"";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Testingmethod.class);
        List testingMethod = query.list();
        session.close();
        return testingMethod;
    }
    
     public static List<Testingmethod> obterTestingmethodPerName(int idTestingClass, String name) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `testingmethod` WHERE `testingClass_idtestingClass` = " + idTestingClass + " AND `name` = \"" + name + "\"";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Testingmethod.class);
        List testingMethod = query.list();
        session.close();
        return testingMethod;
    }
     
     public static List<Testingmethod> obterTestingmethodName(String name) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `testingmethod` WHERE `name` = \"" + name + "\"";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Testingmethod.class);
        List testingMethod = query.list();
        session.close();
        return testingMethod;
    }
    
    public static Testingmethod obterTestingmethod(int idTestingmethod) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Testingmethod testingMethod = (Testingmethod) session.load(Testingmethod.class, idTestingmethod);

        return testingMethod;
    }

    public static void gravarTestingmethod(Testingmethod testingMethod) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.save(testingMethod);
        transaction.commit();
    }

    public static void editarTestingmethod(Testingmethod testingMethod) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(testingMethod);
        transaction.commit();
    }

    public static void excluirTestingmethod(Testingmethod testingMethod) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(testingMethod);
        transaction.commit();
    }

}
