package ORM.Manager;

import ORM.Entity.testEntity;
import Utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class testManager {
    private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();

    public void CreateTest(){
        testEntity test = new testEntity();
        test.setId(1);
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(test);
        session.getTransaction().commit();
        session.close();
    }

}
