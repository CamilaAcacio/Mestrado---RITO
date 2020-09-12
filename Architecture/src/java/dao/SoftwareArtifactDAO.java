package dao;

import java.sql.SQLException;
import java.util.List;
import model.Softwareartifact;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Camila
 */
public class SoftwareArtifactDAO {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    public static List<Softwareartifact> obterSoftwareartifact() throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `softwareartifact`";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Softwareartifact.class);
        List softwareArtifact = query.list();
        session.close();
        return softwareArtifact;
    }
    
    public static List<Softwareartifact> obterSoftwareartifactPerId(String idProject) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `softwareartifact` WHERE `idsoftwareArtifact` = '"+idProject+ "'";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Softwareartifact.class);
        List softwareArtifact = query.list();
        session.close();
        return softwareArtifact;
    }

    public static void gravarSoftwareartifact(Softwareartifact softwareArtifact) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.save(softwareArtifact);
        transaction.commit();

    }

    public static void editarSoftwareartifact(Softwareartifact softwareArtifact) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(softwareArtifact);
        transaction.commit();
    }

    public static void excluirSoftwareartifact(Softwareartifact softwareArtifact) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(softwareArtifact);
        transaction.commit();
    }

}

