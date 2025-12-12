import model.Direccion;
import model.Trabajador;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Trabajador trabajador = new Trabajador("MAria", "Lopez", 20, "maria@gmail.com",
                30000, new Direccion("Madrid", "Madrid", "C/Madrid"), "comentario",
                false);
        // 1 - creo la sesionfactory
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        // 2 - creo la session
        Session session = sessionFactory.openSession();
        // 3 - creo una transaccion contra base de datos
        Transaction transaction = session.beginTransaction();
        // 4 - realizo la transaccion correspondiente -> INSERT INTO
        // CREATE
        // session.persist(trabajador);
        // SELECT -> id / where
        /* Trabajador trabajadorSelect = session. find(Trabajador.class,4);
        if (trabajadorSelect != null){
            System.out.println("Los datos del trabajador son");
            System.out.println(trabajadorSelect.getNombre());
            System.out.println(trabajadorSelect.getAntiguedad());
            System.out.println(trabajadorSelect.getCorreo());
            System.out.println(trabajadorSelect.getSalario());
        } else {
            System.out.println("El usuario es nulo, no lo he encontrado");
        }*/
        // UPDATE -> id / where
        // Trabajador trabajadorSelect = session. find(Trabajador.class,1);
        // trabajadorSelect.setSalario(trabajadorSelect.getSalario()+20000);
        // DELETE -> id / where
        Trabajador trabajadorSelect = session. find(Trabajador.class,1);
        session.remove(trabajadorSelect);
        // 5 - realizo el commit de la transaccion
        transaction.commit();
        // 6 - cierro session y session factory
        session.close();
        sessionFactory.close();


    }
}
