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
import model.Agent;
import model.Testingclass;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

public class AgentDAO {

    public static List<Agent> obterAgent() throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `agent`";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Agent.class);
        List agent = query.list();
        session.close();
        return agent;
    }

    public static List<Agent> obterAgent(String idAgent) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `agent` WHERE `idagent` = '" +idAgent+"'";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Agent.class);
        List agent = query.list();
        session.close();
        return agent;
    }

    public static void gravarAgent(Agent agent) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
//      Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.save(agent);
        transaction.commit();
        //   session.close();
    }

    public static void editarAgent(Agent agent) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(agent);
        transaction.commit();
    }

    public static void excluirAgent(Agent agent) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(agent);
        transaction.commit();
    }

}
