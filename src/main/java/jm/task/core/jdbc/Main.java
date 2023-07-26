package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();

        List<User> users = new ArrayList<>();

        userDaoHibernate.createUsersTable();

        userDaoHibernate.saveUser("Ivan", "Ivanov", (byte) 23);
        System.out.println("Ivan добавлен в базу данных");
        userDaoHibernate.saveUser("Petr", "Petrov", (byte) 42);
        System.out.println("Petr добавлен в базу данных");
        userDaoHibernate.saveUser("Gennady", "Gennadiev", (byte) 12);
        System.out.println("Gennady добавлен в базу данных");
        userDaoHibernate.saveUser("Alexander", "Alexandrov", (byte) 54);
        System.out.println("Alexander добавлен в базу данных");


        System.out.println(userDaoHibernate.getAllUsers());

        userDaoHibernate.cleanUsersTable();

        userDaoHibernate.dropUsersTable();






    }
}
