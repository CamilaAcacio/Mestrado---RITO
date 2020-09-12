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
import model.Composedof;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

public class ComposedofDAO {

    public static List<Composedof> obterComposedof() throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `composedof`";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Composedof.class);
        List composedof = query.list();
        session.close();
        return composedof;
    }

    public static List<Composedof> obterComposedofPorId(int id) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Composedof> composedofs = session.createQuery("from composedof where idcomposedof ='" + id + "'").list();
        for (Composedof a : composedofs) {
            System.out.println(a.getIdcomposedOf());
        }

        return composedofs;
    }

    public static Composedof obterComposedof(int idComposedof) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Composedof composedof = (Composedof) session.load(Composedof.class, idComposedof);

        return composedof;
    }

    public static void gravarComposedof(Composedof composedof) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.save(composedof);
        transaction.commit();

    }

    public static void editarComposedof(Composedof composedof) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(composedof);
        transaction.commit();
    }

    public static void excluirComposedof(Composedof composedof) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(composedof);
        transaction.commit();
    }

}
