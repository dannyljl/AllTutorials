package EJB;

import javax.enterprise.context.RequestScoped;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;

public interface SessionFactoryTemplate {

    public Session getCurrentSession();

    public Session openSession();

    public StatelessSession openStatelessSession();

}
