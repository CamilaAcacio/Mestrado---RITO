/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Sourcecode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author Camila
 */
public class SourceCodeDAO {

    public static List<Sourcecode> obterSourcecode() throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `sourcecode`";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Sourcecode.class);
        List sourceCode = query.list();
        session.close();
        return sourceCode;
    }

    public static List<Sourcecode> obterSourcecodeName(String name) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `sourcecode` WHERE `name` = \"" + name + "\"";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Sourcecode.class);
        List sourceCode = query.list();
        session.close();
        return sourceCode;
    }
    

    public static Sourcecode obterSourcecode(int idSourcecode) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Sourcecode sourceCode = (Sourcecode) session.load(Sourcecode.class, idSourcecode);

        return sourceCode;
    }

    public static void gravarSourcecode(Sourcecode sourceCode) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.save(sourceCode);
        transaction.commit();
    }

    public static void editarSourcecode(Sourcecode sourceCode) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(sourceCode);
        transaction.commit();
    }

    public static void excluirSourcecode(Sourcecode sourceCode) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(sourceCode);
        transaction.commit();
    }

}
