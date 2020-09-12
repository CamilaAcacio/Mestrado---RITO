/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Environment;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author Camila
 */
public class EnvironmentDAO {

    public static List<Environment> obterEnvironment() throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `environment`";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Environment.class);
        List environment = query.list();
        session.close();
        return environment;
    }

    public static List<Environment> obterEnvironment(String idEnvironment) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `environment` WHERE `idenvironment` = '" + idEnvironment + "'";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Environment.class);
        List environment = query.list();
        session.close();
        return environment;
    }

    public static void gravarEnvironment(Environment environment) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.save(environment);
        transaction.commit();
    }

    public static void editarEnvironment(Environment environment) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(environment);
        transaction.commit();
    }

    public static void excluirEnvironment(Environment environment) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(environment);
        transaction.commit();
    }

}
