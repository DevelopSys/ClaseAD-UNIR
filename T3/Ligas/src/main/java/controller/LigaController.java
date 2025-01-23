package controller;

import dao.*;
import database.HibernateUtil;
import model.*;
import org.hibernate.Session;

import java.util.List;

public class LigaController {
    private EntrenadorDAO entrenadorDAO;
    private EquipoDAO equipoDAO;
    private LigasDAO ligasDAO;
    private JugadorDAO jugadorDAO;
    private CompeticionDAO competicionDAO;

    public LigaController() {
        entrenadorDAO = new EntrenadorDAO();
        equipoDAO = new EquipoDAO();
        ligasDAO = new LigasDAO();
        jugadorDAO = new JugadorDAO();
        competicionDAO = new CompeticionDAO();
    }

    public void agregarEntrenador(Entrenador entrenador) {

        // este entrenador tiene el titulo valido
        // este entrenador es valida para el puesto
        // todo ok
        if (entrenador.getCalificacion() < 10) {
            System.out.println("No vale para este equipo");
        } else {
            entrenadorDAO.agregarEntrenador(entrenador);
        }

        // fallo en el entrenador

    }

    public void contratarEntrenador(int idEntrenador, int idEquipo) {
        Entrenador entrenador = entrenadorDAO.obtenerEntranador(idEntrenador);
        // System.out.println(entrenador.getNombre());
        if (entrenador.getTitulos() > 4) {
            // lo contrato
            Equipo equipo = equipoDAO.getEquipo(idEquipo);
            equipo.setEntrenador(entrenador);
            equipoDAO.actualizarEquipo(equipo);

        } else {
            // si la cantidad de valoracion no es menor que 10
            System.out.println("Entrenador no valido para el puesto");
        }
    }

    public void darAltaLiga(Liga liga) {
        ligasDAO.crearLiga(liga);
    }

    public void inscribirseLiga(int idEquipo, int idLiga) {
        Equipo equipo = equipoDAO.getEquipo(idEquipo);
        Liga liga = ligasDAO.getLiga(idLiga);
        equipo.setLiga(liga);
        equipoDAO.actualizarEquipo(equipo);
    }

    public void crearJugador(Jugador jugador, int idPosicion) {
        Session session = new HibernateUtil().getSessionFactory().getCurrentSession();
        ;
        session.beginTransaction();
        Posicion posicion = session.get(Posicion.class, idPosicion);
        session.getTransaction().commit();
        session.close();
        jugadorDAO.crearJugador(jugador, posicion);
    }

    public void contratarJugador(int idJugador, int idEquipo) {
        Equipo equipo = equipoDAO.getEquipo(idEquipo);
        Jugador jugador = jugadorDAO.obtenerJugador(idJugador);
        // logica de la contratacion
        jugador.setEquipo(equipo);
        jugadorDAO.actualizarJugador(jugador);
    }

    public void analizarPlantilla(int id) {
        List<Jugador> jugadores = equipoDAO.obtenerPlantilla(id);

        for (Jugador jugador : jugadores) {
            System.out.println(jugador.getNombre());


        }

    }

    public void getEquiposCompeticion(int id){
        List<Equipo> list = competicionDAO.getEquiposCompeticion(id);
        for (Equipo equipo: list) {
            System.out.println(equipo.getNombre());
        }
    }
}
