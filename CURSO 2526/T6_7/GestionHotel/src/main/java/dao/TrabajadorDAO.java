package dao;

import model.Direccion;
import model.Trabajador;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.UnionSubclass;

public class TrabajadorDAO {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public void insertarUsuario(Trabajador trabajador){
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.persist(trabajador);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    public Trabajador getTrabajador(int id){
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Trabajador trabajador = session.find(Trabajador.class,id);
        transaction.commit();
        session.close();
        sessionFactory.close();
        return trabajador;
    }

    public void eliminarUsuario(int id){
        Trabajador trabajador = getTrabajador(id);

        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        // Trabajador trabajador = session.find(Trabajador.class,id);
        // DELETE FROM Trabajadores WHERE id = 1;
        session.remove(trabajador);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    public void actualizarUsuario(int id, int salario){
        Trabajador trabajador = getTrabajador(id);
        // System.out.println(trabajador.getCorreo());
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        // UPDATE FROM Trabajadores SET salario = 60000 WHERE id = 1
        trabajador.setSalario(salario);
        // session.remove(trabajador);
        session.merge(trabajador);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
