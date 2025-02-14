package org.example.gestorligas.dao;

import lombok.NoArgsConstructor;
import org.example.gestorligas.database.HibernateUtils;
import org.example.gestorligas.model.Jugador;
import org.example.gestorligas.model.Usuario;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

@NoArgsConstructor
public class JugadorDAO {

    private Session session;

    public List<Jugador> getAllJugadores(){

        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        // SELECT * -> foreach / filter -> si hay uno en la lista -> loginok
        // SELECT * FROM usuarios WHERE username = correo AND password = pass
        Query<Jugador> query = session.createQuery("FROM Jugador");
        List<Jugador> listaJugadores = query.list();
        session.getTransaction().commit();
        session.close();

        return listaJugadores;
    }

    public void removeJugador(Jugador jugador){

        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.remove(jugador);
        session.getTransaction().commit();
        session.close();

    }

    public void addJugador(Jugador jugador){

        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(jugador);
        session.getTransaction().commit();
        session.close();

    }


}
