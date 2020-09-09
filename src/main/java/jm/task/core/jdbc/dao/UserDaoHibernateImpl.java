package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    SessionFactory sessionFactory;

    public UserDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }

    @Override
    public void createUsersTable() {

    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction(); // save the student object
            session.save(user); // commit transaction
            transaction.commit(); // выполняет все дейсвтвия с транзакцией
        } finally {
            session.close();
        }

    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("delete from user where id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit(); // завершение транзакции
        } finally {
            session.close();
        }

    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            List<User> users = session.createSQLQuery("select * from user")
                    .addEntity(User.class)
                    .list();
            transaction.commit();
            return users;
        } finally {
            session.close();
        }
    }

    @Override
    public void cleanUsersTable() {

        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("delete from user")
                    .executeUpdate();
            transaction.commit();
        } finally {
            session.close();
        }

    }
}
