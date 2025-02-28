package org.example.gestorligas.dao;

import lombok.NoArgsConstructor;
import org.example.gestorligas.database.HibernateUtils;
import org.example.gestorligas.model.Usuario;
import org.hibernate.Session;
import org.hibernate.query.Query;

@NoArgsConstructor
public class UsuarioDAO {

    private Session session;

    public Usuario getUsuarioLogin(String correo, String pass){

        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        // SELECT * -> foreach / filter -> si hay uno en la lista -> loginok
        // SELECT * FROM usuarios WHERE username = correo AND password = pass
        Query<Usuario> queryUsuario = session.createQuery("FROM Usuario u WHERE u.correo = :correo AND u.pass = :pass");
        queryUsuario.setParameter("correo",correo);
        queryUsuario.setParameter("pass",pass);
        Usuario usuario = queryUsuario.getSingleResult();
        session.getTransaction().commit();
        session.close();

        return usuario;
    }


}
