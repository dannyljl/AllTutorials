package ORM.Manager;

import ORM.Entity.testEntity;
import ORM.HibernateUtility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class testManager {
    private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();

    public void CreateTest(){
        testEntity test = new testEntity();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(test);
        session.getTransaction().commit();
        session.close();
    }

}
