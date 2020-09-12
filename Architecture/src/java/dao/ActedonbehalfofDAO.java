package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Actedonbehalfof;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

public class ActedonbehalfofDAO {

    public static List<Actedonbehalfof> obterActedonbehalfof() throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `actedonbehalfof`";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Actedonbehalfof.class);
        List actedonbehalfof = query.list();
        session.close();
        return actedonbehalfof;
    }

    public static List<Actedonbehalfof> obterActedonbehalfofPorId(int id) throws ClassNotFoundException, SQLException {
         Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `actedonbehalfof` WHERE `idactedOnBehalfOf` = '"+id+"'";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Actedonbehalfof.class);
        List actedonbehalfof = query.list();
        session.close();
        return actedonbehalfof;
    }

    public static Actedonbehalfof obterActedonbehalfof(int idActedonbehalfof) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Actedonbehalfof actedonbehalfof = (Actedonbehalfof) session.load(Actedonbehalfof.class, idActedonbehalfof);

        return actedonbehalfof;
    }

    public static void gravarActedonbehalfof(Actedonbehalfof actedonbehalfof) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.save(actedonbehalfof);
        transaction.commit();

    }

    public static void editarActedonbehalfof(Actedonbehalfof actedonbehalfof) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(actedonbehalfof);
        transaction.commit();
    }

    public static void excluirActedonbehalfof(Actedonbehalfof actedonbehalfof) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(actedonbehalfof);
        transaction.commit();
    }

}
