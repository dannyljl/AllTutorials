package ORM.Manager;

import DTO.UserDTO;
import ORM.Entity.UserEntity;
import Utility.MySessionFactory;
import org.hibernate.Session;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class LoginManager {

    @Inject
    private MySessionFactory mySessionFactory;


    public UserDTO attemptLogin(String username, String password){
        UserEntity userEntity = null;

        Session session = mySessionFactory.getCurrentSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
            Root<UserEntity> root = criteriaQuery.from(UserEntity.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("username"), username),criteriaBuilder.equal(root.get("password"),(password)));
            userEntity = session.createQuery(criteriaQuery).uniqueResult();

        return new UserDTO(userEntity);


    }

    public UserEntity CheckAvailable(String username){
        UserEntity userEntity = null;


        Session session = mySessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
        Root<UserEntity> root = criteriaQuery.from(UserEntity.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("username"), username));
        userEntity = session.createQuery(criteriaQuery).uniqueResult();

        return userEntity;
    }

    public UserEntity CreateUser(String username, String password){
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password);
        Session session = mySessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.save(user);
        session.getTransaction().commit();
        return user;
    }
}
