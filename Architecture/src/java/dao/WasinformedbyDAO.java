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
import model.Wasinformedby;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author Camila
 */
public class WasinformedbyDAO {

        public static List<Wasinformedby> obterWasinformedby() throws ClassNotFoundException, SQLException {
            Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List<Wasinformedby> wasinformedbys = session.createCriteria(Wasinformedby.class).list();
            return wasinformedbys;
        }

        public static List<Wasinformedby> obterWasinformedbyPorId(int id) throws ClassNotFoundException, SQLException {
            Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List<Wasinformedby> wasinformedbys = session.createQuery("from wasinformedby where idwasinformedby ='" + id + "'").list();
            for (Wasinformedby a : wasinformedbys) {
                System.out.println(a.getIdwasInformedBy());
            }

            return wasinformedbys;
        }

        public static Wasinformedby obterWasinformedby(int idWasinformedby) throws ClassNotFoundException, SQLException {
            Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Wasinformedby wasinformedby = (Wasinformedby) session.load(Wasinformedby.class, idWasinformedby);

            return wasinformedby;
        }

        public static void gravarWasinformedby(Wasinformedby wasinformedby) throws SQLException, ClassNotFoundException {
            Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.clear();
            session.save(wasinformedby);
            transaction.commit();

        }

        public static void editarWasinformedby(Wasinformedby wasinformedby) throws SQLException, ClassNotFoundException {
            Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.clear();
            session.update(wasinformedby);
            transaction.commit();
        }

        public static void excluirWasinformedby(Wasinformedby wasinformedby) throws SQLException, ClassNotFoundException {
            Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.clear();
            session.delete(wasinformedby);
            transaction.commit();
        }

    }
