package dao;

import database.HibernateUtil;
import model.Cliente;
import model.Habitacion;
import org.hibernate.Session;

public class ClienteDAO {

    private Session session;

    public void crearCliente(Cliente cliente, int id){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(cliente);
        Habitacion habitacion = session.get(Habitacion.class,id);
        cliente.setHabitacion(habitacion);
        session.merge(cliente);
        session.getTransaction().commit();
        session.close();
    }
}
