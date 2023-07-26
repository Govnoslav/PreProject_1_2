package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String createTable = """
                CREATE TABLE IF NOT EXISTS users (
                id bigint AUTO_INCREMENT,
                name varchar(255),
                last_name varchar(255),
                age tinyint,
                PRIMARY KEY(id)
                )
                """;

        try (Session session = Util.openSession();) {
            session.beginTransaction();
            session.createNativeQuery(createTable).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void dropUsersTable() {
        String dropTable = """
                DROP TABLE IF EXISTS users
                """;
        try (Session session = Util.openSession();) {
            session.beginTransaction();
            session.createNativeQuery(dropTable).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.openSession();) {
            session.beginTransaction();

            session.saveOrUpdate(new User(name, lastName, age));

            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.openSession();) {
            session.beginTransaction();

            Optional.ofNullable(session.get(User.class, id)).ifPresent(session::delete);


            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;
        try (Session session = Util.openSession();) {
            session.beginTransaction();

            users = session.createQuery("FROM User", User.class).list();

            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {
        String clearTable = """
                DELETE FROM users
                """;
        try (Session session = Util.openSession();) {
            session.beginTransaction();
            session.createNativeQuery(clearTable).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
