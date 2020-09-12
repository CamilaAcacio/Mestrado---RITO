/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Commitactivity;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author Camila
 */
public class CommitDAO {

    public static List<Commitactivity> obterCommitActivity() throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `commitactivity`";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Commitactivity.class);
        List commitactivitys = query.list();
        session.close();
        return commitactivitys;
    }

    public static List<Commitactivity> obterCommitActivityPorId(String id) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `commitactivity` WHERE `idcommitActivity` = '" + id + "'";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Commitactivity.class);
        List commitactivitys = query.list();
        session.close();
        return commitactivitys;
    }

    public static Commitactivity obterCommitActivity(int idCommitActivity) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Commitactivity commitActivity = (Commitactivity) session.load(Commitactivity.class, idCommitActivity);

        return commitActivity;
    }

    public static void gravarCommitActivity(Commitactivity commitActivity) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.save(commitActivity);
        transaction.commit();
    }

    public static void editarCommitActivity(Commitactivity commitActivity) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(commitActivity);
        transaction.commit();
    }

    public static void excluirCommitActivity(Commitactivity commitActivity) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(commitActivity);
        transaction.commit();
    }

}
