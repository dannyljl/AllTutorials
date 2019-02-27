package ORM.Manager;

import ORM.Entity.KweetEntity;
import ORM.Entity.UserEntity;
import Utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UserManager {

    private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();

    public UserEntity getUser(int id){
        Session session = sessionFactory.openSession();
        UserEntity user = session.get(UserEntity.class, id);
        session.close();
        return user;
    }

    public void CreateFollower(int followed, int follower){
        UserEntity user1 = new UserEntity();
        UserEntity user2 = new UserEntity();
        user1.setUserId(followed);
        user2.setUserId(follower);
        user1.AddFollower(user2);

        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.merge(user1);
        session.getTransaction().commit();
        session.close();
    }
}
