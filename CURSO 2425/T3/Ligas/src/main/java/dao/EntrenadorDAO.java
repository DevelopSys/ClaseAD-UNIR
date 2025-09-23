package dao;

import database.HibernateUtil;
import model.Entrenador;
import org.hibernate.Session;

public class EntrenadorDAO {
    private Session session;

    public void agregarEntrenador(Entrenador entrenador){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(entrenador);
        session.getTransaction().commit();
        session.close();
    }

    public Entrenador obtenerEntranador(int id){

        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Entrenador entrenador = session.get(Entrenador.class,1);
        session.getTransaction().commit();
        session.close();
        return entrenador;
    }
}
