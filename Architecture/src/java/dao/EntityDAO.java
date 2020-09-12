/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Entityprov;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author Camila
 */
public class EntityDAO {

    public static List<Entityprov> obterEntity() throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `entityprov`";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Entityprov.class);
        List entityprov = query.list();
        session.close();
        return entityprov;
    }

    public static List<Entityprov> obterEntity(String idEntity) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `entityprov` WHERE `identity` = '"+idEntity+ "'";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Entityprov.class);
        List entityprov = query.list();
        session.close();
        return entityprov;
    }

    public static void gravarEntity(Entityprov entity) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.save(entity);
        transaction.commit();
    }

    public static void editarEntity(Entityprov entity) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(entity);
        transaction.commit();
    }

    public static void excluirEntity(Entityprov entity) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(entity);
        transaction.commit();
    }

}
