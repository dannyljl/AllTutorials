package ORM.Manager;

import ORM.Entity.KweetEntity;
import ORM.Entity.UserEntity;
import Utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.util.List;

public class KweetManager {

    private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();


    public List<KweetEntity> getTimeLine(int userId){
        Session session = sessionFactory.openSession();
        UserEntity user = session.get(UserEntity.class,userId);

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<KweetEntity> criteriaQuery = criteriaBuilder.createQuery(KweetEntity.class);
        Root<KweetEntity> root = criteriaQuery.from(KweetEntity.class);

        criteriaQuery.select(root).where(
                criteriaBuilder.or(criteriaBuilder.equal(root.get("user"), user)));


        List<KweetEntity> kweets = session.createQuery(criteriaQuery).getResultList();

        for (UserEntity userEntity : user.getFollowing()){
            criteriaQuery.select(root).where(
                    criteriaBuilder.or(criteriaBuilder.equal(root.get("user"), userEntity)));
            kweets.addAll(session.createQuery(criteriaQuery).getResultList());
        }
        return kweets;
    }

    public void CreateKweet(int userid, String text){
        KweetEntity kweet = new KweetEntity();
        UserEntity user = new UserEntity();
        user.setUserId(userid);
        kweet.setContent(text);
        java.util.Date utilDate = new java.util.Date();
        Date date = new Date(utilDate.getTime());
        kweet.setDate(date);
        kweet.setUser(user);
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(kweet);
        session.getTransaction().commit();
        session.close();
    }
}
