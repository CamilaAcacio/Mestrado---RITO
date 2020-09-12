/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Softwarebuild;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author Camila
 */
public class SoftwareBuildDAO {

    public static List<Softwarebuild> obterSoftwarebuild() throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `softwarebuild`";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Softwarebuild.class);
        List softwarebuild = query.list();
        session.close();
        return softwarebuild;
    }

    public static List<Softwarebuild> obterSoftwarebuildPorId(String idBuildexecution) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `softwarebuild` WHERE `idsoftwarebuild` = '" + idBuildexecution + "'";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Softwarebuild.class);
        List softwarebuild = query.list();
        session.close();
        return softwarebuild;
    }
     
    public static Softwarebuild obterSoftwarebuild(int idSoftwarebuild) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Softwarebuild softwareBuild = (Softwarebuild) session.load(Softwarebuild.class, idSoftwarebuild);

        return softwareBuild;
    }

    public static void gravarSoftwarebuild(Softwarebuild softwareBuild) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.save(softwareBuild);
        transaction.commit();
    }

    public static void editarSoftwarebuild(Softwarebuild softwareBuild) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(softwareBuild);
        transaction.commit();
    }

    public static void excluirSoftwarebuild(Softwarebuild softwareBuild) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(softwareBuild);
        transaction.commit();
    }

}
