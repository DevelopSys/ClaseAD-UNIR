package dao;

import database.HibernateUtil;
import model.Liga;
import org.hibernate.Session;

public class LigasDAO {

    Session session;

    // persist -> guarda
    // merge -> actualiza - inserta (id)
    // UPDATE FROM Trabajadores SET nombreXXX = XXX WHERE id=id
    // list -> select (id)
    // remove -> (id)

    // nameQuery // Query -> HQL
    // nativeQuery // Query -> SQL

    public void crearLiga(Liga liga){

        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.getTransaction().commit();
        session.close();

    }
}
