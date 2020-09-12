/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Buildexecution;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author Camila
 */
public class BuildExecutionDAO {

    public static List<Buildexecution> obterBuildexecution() throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `buildexecution`";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Buildexecution.class);
        List buildExecution = query.list();
        session.close();
        return buildExecution;
    }

    public static List<Buildexecution> obterBuildexecution(String idBuildexecution) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `buildexecution` WHERE `idbuildExecution` = '" + idBuildexecution + "'";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Buildexecution.class);
        List buildExecution = query.list();
        session.close();
        return buildExecution;
    }

    public static void gravarBuildexecution(Buildexecution buildExecution) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.save(buildExecution);
        transaction.commit();
    }

    public static void editarBuildexecution(Buildexecution buildExecution) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(buildExecution);
        transaction.commit();
    }

    public static void excluirBuildexecution(Buildexecution buildExecution) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(buildExecution);
        transaction.commit();
    }

}
