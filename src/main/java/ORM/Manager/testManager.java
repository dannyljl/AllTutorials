package ORM.Manager;

import ORM.Entity.KweetEntity;
import ORM.Entity.UserEntity;
import ORM.Entity.testEntity;
import Utility.HibernateUtility;
import Utility.MySessionFactory;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.sql.Date;

@Stateless
public class testManager {

    @Inject
    private MySessionFactory mySessionFactory;

    public void CreateTest(){
        testEntity test = new testEntity();
        Session session = mySessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.save(test);
        session.getTransaction().commit();
    }

    public void CreateUser(){
        UserEntity usertest = new UserEntity();
        Session session = mySessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.save(usertest);
        session.getTransaction().commit();
    }

    public void CreateKweet(){
        KweetEntity kweet = new KweetEntity();
        UserEntity user = new UserEntity();
        user.setUserId(1);
        kweet.setContent("testcontent");
        kweet.setDate(new Date(12));
        kweet.setUser(user);
        Session session = mySessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.save(kweet);
        session.getTransaction().commit();
    }

    public KweetEntity GetKweet(){
        Session session = mySessionFactory.getCurrentSession();
        KweetEntity kweet = session.get(KweetEntity.class,1);
        return kweet;
    }

    public void CreateFollower(){
        UserEntity user1 = new UserEntity();
        UserEntity user2 = new UserEntity();
        user1.setUserId(1);
        user2.setUserId(2);
        user1.AddFollower(user2);

        Session session = mySessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.merge(user1);
        session.getTransaction().commit();
    }

}
