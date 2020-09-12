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
import model.Wasattributedto;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author Camila
 */
public class WasattributedtoDAO {

    
    public static List<Wasattributedto> obterWasattributedto() throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `wasattributedto`";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Wasattributedto.class);
        List wasattributedto = query.list();
        session.close();
        return wasattributedto;
    }
    
    public static Wasattributedto obterWasattributedto(int idWasattributedto) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Wasattributedto wasattributedto = (Wasattributedto) session.load(Wasattributedto.class, idWasattributedto);

        return wasattributedto;
    }

    public static void gravarWasattributedto(Wasattributedto wasattributedto) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.save(wasattributedto);
        transaction.commit();

    }

    public static void editarWasattributedto(Wasattributedto wasattributedto) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(wasattributedto);
        transaction.commit();
    }

    public static void excluirWasattributedto(Wasattributedto wasattributedto) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(wasattributedto);
        transaction.commit();
    }

}

