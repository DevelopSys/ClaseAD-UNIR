package dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import model.Cliente;
import model.Direccion;
import model.Trabajador;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.UnionSubclass;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import java.util.List;

public class TrabajadorDAO {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public void insertarUsuario(Trabajador trabajador) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.persist(trabajador);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    public Trabajador getTrabajador(int id) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Trabajador trabajador = session.find(Trabajador.class, id);
        System.out.println(trabajador.getPerfil().getNombre());
        transaction.commit();
        session.close();
        sessionFactory.close();
        return trabajador;
    }

    public void eliminarUsuario(int id) {
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

    public void actualizarUsuario(int id, int salario) {
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

    public void seleccionarTrabajador(String correoBuscar) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        // SQL -> HQL
        // SELECT * FROM empleados where email=?
        // ps.setString(1,"asdasd")
        /*session.createMutationQuery("FROM Trabajador t WHERE t.correo=:fulanito AND t.nombre =:nombre")
                .setParameter("fulanito", email)
                .setParameter("nombre", "Borja");
        session.createMutationQuery("FROM Trabajador t WHERE t.correo=:?").setParameter(1, email);*/
        // SELECT * FROM empleados WHERE email = email
        Query<Trabajador> query = session.createNamedQuery("Trabajador.getByEmail", Trabajador.class);
        query.setParameter("email", correoBuscar);
        List<Trabajador> listaResultado = query.getResultList();
        for (Trabajador trabajador : listaResultado) {
            System.out.println("Los datos del trabajador son");
            trabajador.mostrarDatos();

        }
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    public void seleccionarTodos() {
        // Trabajador.getAll
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Query<Trabajador> query = session.createNamedQuery("Trabajador.getAll", Trabajador.class);
        List<Trabajador> listaResultados = query.getResultList();
        for (Trabajador trabajador : listaResultados) {
            trabajador.mostrarDatos();
        }
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    public void actualizarCriteria(String correo, int salario) {
        // UPDATE FROM empleados SET salario = salario WHERE correo=correo
        // CriteriaBuilder
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        // CriteriaUpdate
        CriteriaUpdate<Trabajador> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Trabajador.class);
        // Root
        // FROM empleados -> mapeo a la clase asociada
        Root<Trabajador> root = criteriaUpdate.getRoot();
        // SET
        // WHERE
        criteriaUpdate.set(root.get("salario"), salario)
                .where(criteriaBuilder.equal(root.get("correo"), correo));
        // UPDATE FROM empleados SET salario = salario WHERE correo=correo and nombre = nombre
        // criteriaBuilder.and(criteriaBuilder.equal(root.get("correo"), correo),criteriaBuilder.equal(root.get("nombre"), nombre))
        // mutationQuery
        MutationQuery query = session.createMutationQuery(criteriaUpdate);
        int row = query.executeUpdate();
        System.out.println("El numero de filas afectadas es de "+row);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    public void verReservasRealizadas(int id){
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Trabajador trabajador = session.find(Trabajador.class,id);
        for (Cliente cliente:trabajador.getClientes()) {
            System.out.println("El nomrbe del la reserva es "+cliente.getNombre());
        }
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
