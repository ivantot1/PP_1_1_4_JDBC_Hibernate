package jm.task.core.jdbc;


import com.mysql.cj.Session;
import com.mysql.cj.xdevapi.SessionFactory;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
//import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;

public class Main {
    public static void main(String[] args)  {
        // реализуйте алгоритм здесь




        Connection connection = Util.getConnection();
        //UserDao userDao = new UserDaoJDBCImpl();
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        //userDaoHibernate.createUsersTable();
        //userDaoHibernate.dropUsersTable();
        userDaoHibernate.saveUser("Name1", "LastName1", (byte) 2);
        userDaoHibernate.saveUser("Name1", "LastName1", (byte) 2);
        userDaoHibernate.saveUser("Name1", "LastName1", (byte) 2);
        userDaoHibernate.saveUser("Name1", "LastName1", (byte) 2);
//        userDaoHibernate.removeUserById(2);
        userDaoHibernate.getAllUsers();
        //userDaoHibernate.cleanUsersTable();



        //userDao.createUsersTable();
        //userDao.saveUser("Name11", "LastName1", (byte) 2);
        //userDao.dropUsersTable();
        //userDao.cleanUsersTable();
        //userDao.removeUserById((long) 7);
        //userDao.getAllUsers();


    }
}
