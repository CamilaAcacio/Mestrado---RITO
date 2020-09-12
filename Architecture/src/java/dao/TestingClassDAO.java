/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Testingclass;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author Camila
 */
public class TestingClassDAO {

    public static List<Testingclass> obterTestingclass() throws ClassNotFoundException, SQLException {
         Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `testingclass`";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Testingclass.class);
        List testingclass = query.list();
        session.close();
        return testingclass;
    }
    
    public static List<Testingclass> obterTestingclassPerName(String idProject, String name) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `testingclass` WHERE `testingSuite_idtestingSuite` = '"+idProject+ "' AND `name` = \""+name+"\"";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Testingclass.class);
        List testingclass = query.list();
        session.close();
        return testingclass;
    }
    
    public static List<Testingclass> obterTestingclassPerID(int idProject) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `testingclass` WHERE `idtestingClass` = "+idProject+ "";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Testingclass.class);
        List testingclass = query.list();
        session.close();
        return testingclass;
    }

    public static void gravarTestingclass(Testingclass testingClass) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.save(testingClass);
        transaction.commit();
    }

    public static void editarTestingclass(Testingclass testingClass) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(testingClass);
        transaction.commit();
    }

    public static void excluirTestingclass(Testingclass testingClass) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(testingClass);
        transaction.commit();
    }

}
