package model;

import java.io.Serializable;

// interfaz -> clase abstracta con un conjunto de metodos abs (no tienen definicion)
    // usados (obligatoriamente) en las clases que implementan dicha interfaz
public class Usuario implements Serializable {

    private static final Long serialVersionUID = 5862860582351271964L;
    private String  nombre;
    private String correo;
    private transient String pass;

    public Usuario(String nombre, String correo, String pass) {
        this.nombre = nombre;
        this.correo = correo;
        this.pass = pass;
    }

    public Usuario() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
