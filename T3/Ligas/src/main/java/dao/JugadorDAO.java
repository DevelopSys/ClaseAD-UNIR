package dao;

import database.HibernateUtil;
import model.Jugador;
import model.Posicion;
import org.hibernate.Session;
import org.hibernate.query.criteria.JpaConflictUpdateAction;

public class JugadorDAO {

    private Session session;

    public void crearJugador(Jugador jugador, Posicion posicion){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        jugador.setPosicion(posicion);
        session.merge(jugador);
        session.getTransaction().commit();
        session.close();
    }

    public void actualizarJugador(Jugador jugador){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.merge(jugador);
        session.getTransaction().commit();
        session.close();
    }

    public Jugador obtenerJugador(int id){

        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Jugador jugador = session.get(Jugador.class,id);
        session.getTransaction().commit();
        session.close();
        return jugador;
    }
}
