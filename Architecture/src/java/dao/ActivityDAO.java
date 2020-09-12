package dao;

import java.sql.SQLException;
import java.util.List;
import model.Activity;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

public class ActivityDAO {

    public static List<Activity> obterActivity() throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `activity`";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Activity.class);
        List activity = query.list();
        session.close();
        return activity;
    }
    
    public static List<Activity> obterActivityPerId(String idProject) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `activity` WHERE `idactivity` = '"+idProject+ "'";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Activity.class);
        List activity = query.list();
        session.close();
        return activity;
    }

    public static void gravarActivity(Activity activity) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.save(activity);
        transaction.commit();

    }

    public static void editarActivity(Activity activity) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(activity);
        transaction.commit();
    }

    public static void excluirActivity(Activity activity) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(activity);
        transaction.commit();
    }

}
