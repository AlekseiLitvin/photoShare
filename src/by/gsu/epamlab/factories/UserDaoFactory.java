package by.gsu.epamlab.factories;

import by.gsu.epamlab.dao.users.UserDao;
import by.gsu.epamlab.dao.users.UserDaoHibernate;

public class UserDaoFactory {
    public static UserDao getClassFromFactory(){
        return new UserDaoHibernate();
//        return new UserDaoMemory();
    }
}
