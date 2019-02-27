package ORM.Manager;

import ORM.Entity.UserEntity;
import Utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class LoginManager {

    private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();


    public UserEntity attemptLogin(String username, String password){
        UserEntity userEntity = null;


            Session session = sessionFactory.openSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
            Root<UserEntity> root = criteriaQuery.from(UserEntity.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("username"), username),criteriaBuilder.equal(root.get("password"),password));
            userEntity = session.createQuery(criteriaQuery).uniqueResult();

        return userEntity;


    }

    public UserEntity CheckAvailable(String username){
        UserEntity userEntity = null;


        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
        Root<UserEntity> root = criteriaQuery.from(UserEntity.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("username"), username));
        userEntity = session.createQuery(criteriaQuery).uniqueResult();

        return userEntity;
    }

    public void CreateUser(String username, String password){
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password);
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }
}
