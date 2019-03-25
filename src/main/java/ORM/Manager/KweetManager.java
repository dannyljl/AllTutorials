package ORM.Manager;

import DTO.KweetDTO;
import ORM.Entity.KweetEntity;
import ORM.Entity.UserEntity;
import Utility.MySessionFactory;
import org.hibernate.Session;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class KweetManager {

    @Inject
    private MySessionFactory mySessionFactory;


    public List<KweetDTO> getTimeLine(int userId){
        Session session = mySessionFactory.getCurrentSession();
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
        List<KweetDTO> kweetsDTO = new ArrayList<>();

        for (KweetEntity kweet : kweets){
            kweetsDTO.add(new KweetDTO(kweet));
        }
        return kweetsDTO;
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
        Session session = mySessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.save(kweet);
        session.getTransaction().commit();
    }

    public List<KweetDTO> SearchKweet(String searchContent){
        Session session = mySessionFactory.getCurrentSession();
        List<KweetDTO> kweets = new ArrayList<>();
        String query = "from KweetEntity where content like ?1";
        Query query2 = session.createQuery(query).setParameter(1,"%" + searchContent + "%");
        for (KweetEntity kweet : (List<KweetEntity>) query2.getResultList()){
            kweets.add(new KweetDTO(kweet));
        }
        return kweets;
    }
}
