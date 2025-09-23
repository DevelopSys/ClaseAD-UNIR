package dao;

import database.HibernateUtil;
import model.Cliente;
import model.Habitacion;
import model.Trabajador;
import org.hibernate.Session;

public class HabitacionDAO {

    Session session;

    public void crearHabitacion(Habitacion habitacion){
        session =new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(habitacion);
        session.getTransaction().commit();
        session.close();
    }

    public void getTrabajardorHabitacion(int id){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Habitacion habitacion = session.get(Habitacion.class,id);
        System.out.println("El trabajador que se aloja en la habitacion es ");
        System.out.println(habitacion.getTrabajador().getNombre() +" "+habitacion.getTrabajador().getApellido() );

        session.getTransaction().commit();
        session.close();
    }

    public void getAllClientes(int id){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Habitacion habitacion = session.get(Habitacion.class,id);
        for (Cliente cliente : habitacion.getLista()) {
            System.out.println(cliente.getNombre());
        }

        session.getTransaction().commit();
        session.close();
    }
}
