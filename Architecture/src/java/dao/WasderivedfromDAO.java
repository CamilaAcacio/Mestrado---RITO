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
import model.Wasderivedfrom;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author Camila
 */
public class WasderivedfromDAO {

        public static List<Wasderivedfrom> obterWasderivedfrom() throws ClassNotFoundException, SQLException {
            Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `wasderivedfrom`";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Wasderivedfrom.class);
        List wasderivedfrom = query.list();
        session.close();
        return wasderivedfrom;
        }

        public static List<Wasderivedfrom> obterWasderivedfromPorId(int id) throws ClassNotFoundException, SQLException {
            Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List<Wasderivedfrom> wasderivedfroms = session.createQuery("from wasderivedfrom where idwasderivedfrom ='" + id + "'").list();
            for (Wasderivedfrom a : wasderivedfroms) {
                System.out.println(a.getIdwasDerivedFrom());
            }

            return wasderivedfroms;
        }

        public static Wasderivedfrom obterWasderivedfrom(int idWasderivedfrom) throws ClassNotFoundException, SQLException {
            Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Wasderivedfrom wasderivedfrom = (Wasderivedfrom) session.load(Wasderivedfrom.class, idWasderivedfrom);

            return wasderivedfrom;
        }

        public static void gravarWasderivedfrom(Wasderivedfrom wasderivedfrom) throws SQLException, ClassNotFoundException {
            Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.clear();
            session.save(wasderivedfrom);
            transaction.commit();

        }

        public static void editarWasderivedfrom(Wasderivedfrom wasderivedfrom) throws SQLException, ClassNotFoundException {
            Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.clear();
            session.update(wasderivedfrom);
            transaction.commit();
        }

        public static void excluirWasderivedfrom(Wasderivedfrom wasderivedfrom) throws SQLException, ClassNotFoundException {
            Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.clear();
            session.delete(wasderivedfrom);
            transaction.commit();
        }

    }