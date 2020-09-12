/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Hasenvironment;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

public class HasenvironmentDAO {

    public static List<Hasenvironment> obterHasenvironment() throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `hasenvironment`";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Hasenvironment.class);
        List wasinformedby = query.list();
        session.close();
        return wasinformedby;
    }

    public static List<Hasenvironment> obterHasenvironmentPorId(int id) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Hasenvironment> hasenvironments = session.createQuery("from hasenvironment where idhasenvironment ='" + id + "'").list();
        for (Hasenvironment a : hasenvironments) {
            System.out.println(a.getIdhasEnvironment());
        }

        return hasenvironments;
    }

    public static Hasenvironment obterHasenvironment(int idHasenvironment) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Hasenvironment hasenvironment = (Hasenvironment) session.load(Hasenvironment.class, idHasenvironment);

        return hasenvironment;
    }

    public static void gravarHasenvironment(Hasenvironment hasenvironment) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.save(hasenvironment);
        transaction.commit();

    }

    public static void editarHasenvironment(Hasenvironment hasenvironment) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(hasenvironment);
        transaction.commit();
    }

    public static void excluirHasenvironment(Hasenvironment hasenvironment) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(hasenvironment);
        transaction.commit();
    }

}

