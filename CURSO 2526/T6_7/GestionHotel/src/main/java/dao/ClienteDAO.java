package dao;

import model.Cliente;
import model.Perfil;
import model.Trabajador;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClienteDAO {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;


    public void addCliente(Cliente cliente) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.persist(cliente);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    public void addReserva(int idCliente, int idTrabajador) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Trabajador trabajador = session.find(Trabajador.class,idTrabajador);
        Cliente cliente = session.find(Cliente.class,idCliente);
        cliente.addTrabajador(trabajador);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }


}
