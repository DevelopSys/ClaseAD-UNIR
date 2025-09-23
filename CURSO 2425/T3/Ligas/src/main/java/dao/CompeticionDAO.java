package dao;

import database.HibernateUtil;
import model.Competicion;
import model.Equipo;
import org.hibernate.Session;

import java.util.List;

public class CompeticionDAO {

    private Session session;

    public List<Equipo> getEquiposCompeticion(int id){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Competicion competicion = session.get(Competicion.class,id);
        session.getTransaction().commit();
        session.close();
        return competicion.getEquipos();
    }
}
