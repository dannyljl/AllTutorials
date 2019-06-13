package ORM.Manager;

import DTO.FollowerDTO;
import DTO.KweetDTO;
import DTO.UserDTO;
import ORM.Entity.KweetEntity;
import ORM.Entity.TokenEntity;
import ORM.Entity.UserEntity;
import Utility.MySessionFactory;
import com.google.gson.Gson;
import org.hibernate.Session;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;
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
        Gson gson = new Gson();
        Session session = mySessionFactory.getCurrentSession();
        UserEntity user = session.get(UserEntity.class, id);
        System.out.println("get user simple right?" + gson.toJson(user));
        return user;
    }

    public void CreateFollower(int followed, int follower){
        UserEntity user1 = new UserEntity();
        UserEntity user2 = new UserEntity();
        user1 = getUserEntity(followed);
        user2 = getUserEntity(follower);
        user2.addFollowing(user1);
        Session session = mySessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.merge(user2);
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
