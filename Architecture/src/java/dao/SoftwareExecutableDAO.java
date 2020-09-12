/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Softwareexecutable;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author Camila
 */
public class SoftwareExecutableDAO {

    public static List<Softwareexecutable> obterSoftwareexecutable() throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `softwareexecutable`";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Softwareexecutable.class);
        List softwareexecutable = query.list();
        session.close();
        return softwareexecutable;
    }

    public static List<Softwareexecutable> obterSoftwareexecutablePorId(String idBuildexecution) throws ClassNotFoundException, SQLException {
       Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `softwareexecutable` WHERE `idsoftwareexecutable` = '" + idBuildexecution + "'";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Softwareexecutable.class);
        List softwareexecutable = query.list();
        session.close();
        return softwareexecutable;
    }

    public static Softwareexecutable obterSoftwareexecutable(int idSoftwareexecutable) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Softwareexecutable softwareExecutable = (Softwareexecutable) session.load(Softwareexecutable.class, idSoftwareexecutable);

        return softwareExecutable;
    }

    public static void gravarSoftwareexecutable(Softwareexecutable softwareExecutable) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.save(softwareExecutable);
        transaction.commit();
    }

    public static void editarSoftwareexecutable(Softwareexecutable softwareExecutable) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(softwareExecutable);
        transaction.commit();
    }

    public static void excluirSoftwareexecutable(Softwareexecutable softwareExecutable) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(softwareExecutable);
        transaction.commit();
    }

}
