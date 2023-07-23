package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();


        userService.createUsersTable();

        userService.saveUser("Ivan", "Ivanov", (byte) 23);
        System.out.println("Ivan добавлен в базу данных");
        userService.saveUser("Petr", "Petrov", (byte) 42);
        System.out.println("Petr добавлен в базу данных");
        userService.saveUser("Gennady", "Gennadiev", (byte) 12);
        System.out.println("Gennady добавлен в базу данных");
        userService.saveUser("Alexander", "Alexandrov", (byte) 54);
        System.out.println("Alexander добавлен в базу данных");

        System.out.println(userService.getAllUsers());

        userService.createUsersTable();

        userService.dropUsersTable();






    }
}
