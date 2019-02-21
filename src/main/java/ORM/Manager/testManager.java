package ORM.Manager;

import ORM.Entity.KweetEntity;
import ORM.Entity.UserEntity;
import ORM.Entity.testEntity;
import Utility.HibernateUtility;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Date;

public class testManager {
    private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();

    public void CreateTest(){
        testEntity test = new testEntity();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(test);
        session.getTransaction().commit();
        session.close();
    }

    public void CreateUser(){
        UserEntity usertest = new UserEntity();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(usertest);
        session.getTransaction().commit();
        session.close();
    }

    public void CreateKweet(){
        KweetEntity kweet = new KweetEntity();
        UserEntity user = new UserEntity();
        user.setUserId(1);
        kweet.setContent("testcontent");
        kweet.setDate(new Date(12));
        kweet.setUser(user);
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(kweet);
        session.getTransaction().commit();
        session.close();
    }

    public KweetEntity GetKweet(){
        Session session = sessionFactory.openSession();
        KweetEntity kweet = session.get(KweetEntity.class,1);
        session.close();
        return kweet;
    }

}
