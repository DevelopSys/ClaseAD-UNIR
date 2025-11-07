package model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private int id;
    private String nombre, mail;
    private int telefono;
    private int idPerfil;

    public Usuario(String nombre, String mail, int telefono, int idPerfil) {
        this.nombre = nombre;
        this.mail = mail;
        this.telefono = telefono;
        this.idPerfil = idPerfil;
    }

    public void mostrarDatos(){
        System.out.println("nombre = " + nombre);
        System.out.println("mail = " + mail);
        System.out.println("telefono = " + telefono);
        System.out.println("idPerfil = " + idPerfil);
    }
}
