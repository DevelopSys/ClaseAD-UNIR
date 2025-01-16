package database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public SessionFactory getSessionFactory(){
        if (sessionFactory ==null){
            createSessionFactory();
        }

        return sessionFactory;
    }

    private void createSessionFactory() {
        sessionFactory = new Configuration().configure().buildSessionFactory();

    }

}
