/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Testingsourcecode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author Camila
 */
public class TestingSourceCodeDAO {

    public static List<Testingsourcecode> obterTestingsourcecode() throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `testingsourcecode`";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Testingsourcecode.class);
        List testingsuite = query.list();
        session.close();
        return testingsuite;
    }

    public static List<Testingsourcecode> obterTestingsourcecodePorId(String id) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `testingsourcecode` WHERE `project_idproject` = "+id+ "";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Testingsourcecode.class);
        List testingsuite = query.list();
        session.close();
        return testingsuite;
    }
    
     public static List<Testingsourcecode> obterTestingsourcecodePorArtifact(String id) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `testingsourcecode` WHERE `testingArtifact_idtestingArtifact` = '"+id+ "'";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Testingsourcecode.class);
        List testingsuite = query.list();
        session.close();
        return testingsuite;
    }
    

    public static Testingsourcecode obterTestingsourcecode(int idTestingsourcecode) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Testingsourcecode testingSourceCode = (Testingsourcecode) session.load(Testingsourcecode.class, idTestingsourcecode);

        return testingSourceCode;
    }

    public static void gravarTestingsourcecode(Testingsourcecode testingSourceCode) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.save(testingSourceCode);
        transaction.commit();
    }

    public static void editarTestingsourcecode(Testingsourcecode testingSourceCode) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(testingSourceCode);
        transaction.commit();
    }

    public static void excluirTestingsourcecode(Testingsourcecode testingSourceCode) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(testingSourceCode);
        transaction.commit();
    }

}
