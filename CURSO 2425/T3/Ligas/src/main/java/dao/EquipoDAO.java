package dao;

import database.HibernateUtil;
import model.Equipo;
import model.Jugador;
import org.hibernate.Session;

import java.util.List;

public class EquipoDAO {

    Session session;

    public Equipo getEquipo(int id){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Equipo equipo = session.get(Equipo.class,id);
        session.getTransaction().commit();
        session.close();
        return equipo;
    }
    public void actualizarEquipo(Equipo equipo){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.merge(equipo);
        session.getTransaction().commit();
        session.close();
    }

    public List<Jugador> obtenerPlantilla(int id){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Equipo equipo = session.get(Equipo.class,id);
        session.getTransaction().commit();
        session.close();
        return equipo.getJugadores();
    }
}
