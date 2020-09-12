/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Wasassociatedwith;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author Camila
 */
public class WasassociatedwithDAO {

    public static List<Wasassociatedwith> obterWasassociatedwith() throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `wasassociatedwith`";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Wasassociatedwith.class);
        List wasassociatedwith = query.list();
        session.close();
        return wasassociatedwith;
    }

    public static List<Wasassociatedwith> obterWasassociatedwithPorId(int id) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Wasassociatedwith> wasassociatedwiths = session.createQuery("from wasassociatedwith where idwasassociatedwith ='" + id + "'").list();
        for (Wasassociatedwith a : wasassociatedwiths) {
            System.out.println(a.getIdwasAssociatedWith());
        }

        return wasassociatedwiths;
    }

    public static Wasassociatedwith obterWasassociatedwith(int idWasassociatedwith) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Wasassociatedwith wasassociatedwith = (Wasassociatedwith) session.load(Wasassociatedwith.class, idWasassociatedwith);

        return wasassociatedwith;
    }

    public static void gravarWasassociatedwith(Wasassociatedwith wasassociatedwith) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.save(wasassociatedwith);
        transaction.commit();
    }

    public static void editarWasassociatedwith(Wasassociatedwith wasassociatedwith) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(wasassociatedwith);
        transaction.commit();
    }

    public static void excluirWasassociatedwith(Wasassociatedwith wasassociatedwith) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(wasassociatedwith);
        transaction.commit();
    }

}
