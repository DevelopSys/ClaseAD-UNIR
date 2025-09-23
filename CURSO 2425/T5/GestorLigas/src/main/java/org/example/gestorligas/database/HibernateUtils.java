package org.example.gestorligas.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtils {

    private static SessionFactory sessionFactory;

    public SessionFactory getSessionFactory(){

        if (sessionFactory == null){
            createSessionFactory();
        }

        return sessionFactory;
    }

    private void createSessionFactory() {

        // TODO crear el file asociado a los recursos
        File file = new File("src/main/resources/org/example/gestorligas/hibernate.cfg.xml");
        sessionFactory = new Configuration()
                .configure(file).buildSessionFactory();
    }

}
