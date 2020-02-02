package DAO;

import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.DBHelper;

import java.util.List;

public class UserHibernateDaoInterface implements DaoInterface {

    private DBHelper dbHelper = DBHelper.getInstance();

    @Override
    public List<User> getAllUser() {
        Session session = dbHelper.createSession();
        Transaction transaction = session.beginTransaction();
        try {
            List<User> user = session.createQuery("FROM User").list();
            transaction.commit();
            return user;
        } finally {
            try {
                session.close();
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addUser(User user) {
        Session session = dbHelper.createSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public User getUserById(long id) {
        Session session = dbHelper.createSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("FROM User WHERE id = '" + id + "'");
            transaction.commit();
            return (User) query.uniqueResult();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public void updateUsersName(long id, String newName) {
        Session session = dbHelper.createSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = getUserById(id);
            user.setName(newName);
            session.update(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void updateUsersAge(long id, int age) {
        Session session = dbHelper.createSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = getUserById(id);
            user.setAge(age);
            session.update(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void deleteUser(long id) {
        Session session = dbHelper.createSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = getUserById(id);
            session.delete(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
