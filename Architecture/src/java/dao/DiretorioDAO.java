/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Diretorio;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author Camila
 */
public class DiretorioDAO {
    
      public static List<Diretorio> obterDiretorio() throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Diretorio> diretorios = session.createCriteria(Diretorio.class).list();
        return diretorios;
    }

    public static List<Diretorio> obterDiretorioPorId(int id) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Diretorio> diretorios = session.createQuery("from diretorioprov where iddiretorio like'" + id + "'").list();
        for (Diretorio a : diretorios) {
         //   System.out.println(a.getIddiretorio());
        }

        return diretorios;
    }

    public static Diretorio obterDiretorio(int idDiretorio) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Diretorio diretorio = (Diretorio) session.load(Diretorio.class, idDiretorio);

        return diretorio;
    }

    public static void gravarDiretorio(Diretorio diretorio) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.save(diretorio);
        transaction.commit();
    }

    public static void editarDiretorio(Diretorio diretorio) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(diretorio);
        transaction.commit();
    }

    public static void excluirDiretorio(Diretorio diretorio) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(diretorio);
        transaction.commit();
    }

    
}
