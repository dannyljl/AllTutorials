package ORM.HibernateUtility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtility {
    private HibernateUtility() {
    }

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null)
            sessionFactory = buildSessionFactory();
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        sessionFactory.close();
    }

    private static SessionFactory buildSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }
}
