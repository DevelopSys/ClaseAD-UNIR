package dao;

import model.Usuario;

import java.util.ArrayList;

public interface UsuarioDAO {
     ArrayList<String> obtenerCorreos();
     ArrayList<Usuario> obtenerPerfil(int idPerfil);

}
