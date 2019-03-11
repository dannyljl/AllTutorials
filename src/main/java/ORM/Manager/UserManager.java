package ORM.Manager;

import DTO.UserDTO;
import Json.UserJson;
import ORM.Entity.KweetEntity;
import ORM.Entity.UserEntity;
import Utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UserManager {

    private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();

    public UserDTO getUser(int id){
        Session session = sessionFactory.openSession();
        UserEntity user = session.get(UserEntity.class, id);
        session.close();

        return new UserDTO(user);
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

    public UserDTO editUser(int id, UserDTO userJson){
        UserEntity user = new UserEntity();
        user.setUserId(id);
        user.setUsername(userJson.getUsername());
        user.setBio(userJson.getBio());
        user.setLocation(userJson.getLocation());
        user.setWeb(userJson.getWeb());

        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.merge(user);
        session.getTransaction().commit();
        session.close();
        return new UserDTO(user);
    }
}
