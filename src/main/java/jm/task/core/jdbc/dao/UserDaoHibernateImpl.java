package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.PersistentObjectException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class UserDaoHibernateImpl implements UserDao {


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            String sql = ("CREATE TABLE users (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                    "name VARCHAR(20) NOT NULL ," +
                    "lastName VARCHAR(30)NOT NULL," +
                    "age TINYINT NOT NULL )");
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction();

        } catch (PersistentObjectException ignored) {
            
        }


    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            String sql = "DROP TABLE users";
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();

        } catch (IllegalArgumentException ignored) {
        }


    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();


        } catch (IllegalArgumentException ignored) {
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            session.getTransaction().commit();


        } catch (IllegalArgumentException ignored) {
        }

    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        } catch (HibernateException e) {
            throw new IllegalArgumentException(e);

        }
    }


    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            String sql = "DELETE FROM users users";
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IllegalArgumentException e) {
        }
    }
}
