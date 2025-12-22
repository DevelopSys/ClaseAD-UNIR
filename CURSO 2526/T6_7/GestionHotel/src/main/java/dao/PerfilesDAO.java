package dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import model.Perfil;
import model.Trabajador;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import java.util.List;

public class PerfilesDAO {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;


    public void getPerfil(int id) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Perfil perfil = session.find(Perfil.class, 1);
        System.out.println(perfil.getNombre());
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    public void getTipoPerfil(int i) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Perfil perfil = session.find(Perfil.class, i);
        for (Trabajador trabajador: perfil.getListaTrabajadores()) {
            System.out.println(trabajador.getNombre());
        }

        transaction.commit();
        session.close();
        sessionFactory.close();
    }


}
