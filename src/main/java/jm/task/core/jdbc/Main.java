package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args)  {
        // реализуйте алгоритм здесь
        Connection connection = Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();
        userDao.saveUser("Name11", "LastName1", (byte) 2);
        //userDao.dropUsersTable();
        //userDao.cleanUsersTable();
        //userDao.removeUserById((long) 7);
        userDao.getAllUsers();


    }
}
