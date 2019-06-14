package Utility;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import EJB.SessionFactoryTemplate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;

@RequestScoped
public class MySessionFactory implements SessionFactoryTemplate {

    @Inject
    private SessionFactory sessionFactory;

    private Session currentSession;

    public Session openSession(){
        return sessionFactory.openSession();
    }

    public Session getCurrentSession(){
        if(currentSession == null){
            currentSession = sessionFactory.openSession();
        }
        return currentSession;
    }

    public StatelessSession openStatelessSession() {
        return sessionFactory.openStatelessSession();
    }

    @PreDestroy
    private void closeSession(){
        if(currentSession!=null && currentSession.isOpen()) {
            currentSession.close();
        }
    }
}
