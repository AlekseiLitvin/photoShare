package by.gsu.epamlab.dao.users;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.model.User;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class UserDaoHibernate implements UserDao {

    @Override
    public User getUser(String login) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq(Constants.LOGIN, login));
            return (User) criteria.uniqueResult();
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean addUser(String login, String password) {
        final String UPDATE_PASSWORD_QUERY = "UPDATE users SET password = ? WHERE login = ?";
        final int PASSWORD_INDEX = 0, LOGIN_INDEX = 1;
        Session session = HibernateUtil.getSession();
        Transaction tr = null;
        try {
            synchronized (UserDaoHibernate.class){
                if (getUser(login) == null){
                    tr = session.beginTransaction();
                    session.save(new User(login));
                    SQLQuery query = session.createSQLQuery(UPDATE_PASSWORD_QUERY);
                    query.setString(PASSWORD_INDEX, password);
                    query.setString(LOGIN_INDEX, login);
                    query.executeUpdate();
                    tr.commit();
                    return true;
                }else {
                    return false;
                }
            }
        }catch (Exception e){
            if (tr != null) {
                tr.rollback();
            }
            throw new DaoException(e);
        }finally {
            session.close();
        }
    }

    @Override
    public boolean checkPassword(String login, String password) {
        final String SELECT_PASSWORD_BY_LOGIN_QUERY = "SELECT password FROM users WHERE login = ?";
        final int LOGIN_INDEX = 0;
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            SQLQuery query = session.createSQLQuery(SELECT_PASSWORD_BY_LOGIN_QUERY);
            query.setString(LOGIN_INDEX, login);
            String userPassword = (String) query.uniqueResult();
            return password.equals(userPassword);
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
