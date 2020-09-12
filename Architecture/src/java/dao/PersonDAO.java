/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Person;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author Camila
 */
public class PersonDAO {

    public static List<Person> obterPerson() throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `person`";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Person.class);
        List persons = query.list();
        session.close();
        return persons;
    }

    public static List<Person> obterPersonPorId(String id) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM `person` WHERE `idperson` = '" + id + "'";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Person.class);
        List persons = query.list();
        session.close();
        return persons;
    }

    public static Person obterPerson(int idPerson) throws ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Person person = (Person) session.load(Person.class, idPerson);

        return person;
    }

    public static void gravarPerson(Person person) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.save(person);
        transaction.commit();

    }

    public static void editarPerson(Person person) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(person);
        transaction.commit();
    }

    public static void excluirPerson(Person person) throws SQLException, ClassNotFoundException {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(person);
        transaction.commit();
    }

}
