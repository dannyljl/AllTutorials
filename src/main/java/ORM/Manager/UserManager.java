package ORM.Manager;

import AccountTypes.AccountType;
import DTO.UserDTO;
import ORM.Entity.TokenEntity;
import ORM.Entity.UserEntity;
import Utility.MySessionFactory;
import org.hibernate.Session;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserManager {

    @Inject
    private MySessionFactory mySessionFactory;

    public UserDTO getUser(int id){
        Session session = mySessionFactory.getCurrentSession();
        UserEntity user = session.get(UserEntity.class, id);

        return new UserDTO(user);
    }

    public UserEntity getUserEntity(int id){
        Session session = mySessionFactory.getCurrentSession();
        UserEntity user = session.get(UserEntity.class, id);

        return user;
    }

    public void CreateFollower(int followed, int follower){
        UserEntity user1 = new UserEntity();
        UserEntity user2 = new UserEntity();
        user1 = getUserEntity(followed);
        user2 = getUserEntity(follower);
        user1.AddFollower(user2);

        Session session = mySessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.merge(user1);
        session.getTransaction().commit();
    }

    public UserDTO editUser(int id, UserDTO userJson){
        UserEntity user = getUserEntity(id);
        user.setName(userJson.getName());
        user.setImage(userJson.getImage());
        user.setUsername(userJson.getUsername());
        user.setBio(userJson.getBio());
        user.setLocation(userJson.getLocation());
        user.setWeb(userJson.getWeb());

        Session session = mySessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.merge(user);
        session.getTransaction().commit();
        return new UserDTO(user);
    }

    public boolean CheckToken(String token){
        Session session = mySessionFactory.getCurrentSession();

        TokenEntity tokenEntity = (TokenEntity) session.createQuery("from TokenEntity where token = :token ")
                                          .setParameter("token",token).getSingleResult();
        if(tokenEntity != null){
            return true;
        }
        return false;
    }

    public int getRoleId(int id){
        Session session = mySessionFactory.getCurrentSession();
        UserEntity user = session.get(UserEntity.class,id);

        return user.getAccountType().ordinal();
    }
}
