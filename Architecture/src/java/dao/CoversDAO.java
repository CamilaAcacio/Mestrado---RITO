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
import model.Covers;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author Camila
 */
public class CoversDAO {

    public static List<Covers> obterCovers() throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `covers`";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Covers.class);
        List covers = query.list();
        session.close();
        return covers;
        
    }

    public static Covers obterCovers(int idCovers) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Covers covers = (Covers) session.load(Covers.class, idCovers);

        return covers;
    }

    public static void gravarCovers(Covers covers) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.save(covers);
        transaction.commit();

    }

    public static void editarCovers(Covers covers) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(covers);
        transaction.commit();
    }

    public static void excluirCovers(Covers covers) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(covers);
        transaction.commit();
    }

}
