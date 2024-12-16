import database.HibernateUtil;
import model.Trabajador;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) {
        // insercion -> insert
        /*Scanner scanner = new Scanner(System.in);
        SessionFactory sessionFactory = new HibernateUtil().getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        System.out.println("Introduce todos los datos del trabajadore - nombre, apellido, direccion,telefono");
        session.persist(
                new Trabajador(scanner.next(),scanner.next(),scanner.next(),scanner.nextInt()));
        session.getTransaction().commit();
        session.close();*/

        // obtencion -> select - ps, rs
        /*
        rs = ps.execute()
        while(rs.next()){
        String n = rs.getString(CASAD.asdasd)
        String a = rs.getString(CASAD.asdasd)
        String d = rs.getString(CASAD.asdasd)
        String t = rs.getString(CASAD.asdasd)
        Trabajador t = new Trabajador(n,a,d,t)
        }
         */
        /*
        SessionFactory sessionFactory = new HibernateUtil().getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Trabajador t = session.get(Trabajador.class,1);
        session.getTransaction().commit();
        System.out.println(t);
        session.close();*/
        // actualizacion
        /*SessionFactory sessionFactory = new HibernateUtil().getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Trabajador trabajador = session.get(Trabajador.class,3);
        // Trabajador trabajador = new Trabajador(3);
        trabajador.setDireccion("Jaen");
        //trabajador.setDireccion("Sevilla");
        session.merge(trabajador);
        session.getTransaction().commit();
        session.close();*/
        // borrado
        /*
        SessionFactory sessionFactory = new HibernateUtil().getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        // Trabajador trabajador = new Trabajador(1);
        Trabajador trabajador = session.get(Trabajador.class,3);
        session.delete(trabajador);
        session.getTransaction().commit();
        session.close();*/

        // seleccion avanzada
        SessionFactory sessionFactory = new HibernateUtil().getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        // t nomeglqtura para un trabajador
        // SELECT * FROM EMPLEADOS
        Query<Trabajador> query = session.createQuery("SELECT t FROM Trabajador t",Trabajador.class);
        List<Trabajador> listaTrabajadores = query.list();
        for (Trabajador trabajador: listaTrabajadores) {
            System.out.println(trabajador);
        }
        session.getTransaction().commit();
        session.close();
    }
}
