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
import model.Wasgeneratedby;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author Camila
 */
public class WasgeneratedbyDAO {

    public static List<Wasgeneratedby> obterWasgeneratedby() throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `wasgeneratedby`";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Wasgeneratedby.class);
        List wasgeneratedby = query.list();
        session.close();
        return wasgeneratedby;
    }

    public static List<Wasgeneratedby> obterWasgeneratedbyPorId(int id) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Wasgeneratedby> wasgeneratedbys = session.createQuery("from wasgeneratedby where idwasgeneratedby ='" + id + "'").list();
        for (Wasgeneratedby a : wasgeneratedbys) {
            System.out.println(a.getIdwasGeneratedBy());
        }

        return wasgeneratedbys;
    }

    public static Wasgeneratedby obterWasgeneratedby(int idWasgeneratedby) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Wasgeneratedby wasgeneratedby = (Wasgeneratedby) session.load(Wasgeneratedby.class, idWasgeneratedby);

        return wasgeneratedby;
    }

    public static void gravarWasgeneratedby(Wasgeneratedby wasgeneratedby) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.save(wasgeneratedby);
        transaction.commit();

    }

    public static void editarWasgeneratedby(Wasgeneratedby wasgeneratedby) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(wasgeneratedby);
        transaction.commit();
    }

    public static void excluirWasgeneratedby(Wasgeneratedby wasgeneratedby) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(wasgeneratedby);
        transaction.commit();
    }

}
