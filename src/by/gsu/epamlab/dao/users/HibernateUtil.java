package by.gsu.epamlab.dao.users;

import by.gsu.epamlab.constants.ConstantsError;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.model.Image;
import by.gsu.epamlab.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration conf = new Configuration()
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Image.class)
                    .configure();

            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
                    .applySettings(conf.getProperties()).buildServiceRegistry();
            sessionFactory = conf.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            System.err.println(ConstantsError.SESSION_FACTORY_ERROR + e);
            throw new DaoException(e);
        }
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void closeSessionFactory() {
        sessionFactory.close();
    }
}