package model;

import java.io.Serializable;

public class Usuario implements Serializable {

    private static final Long serialVersionUID = 12L;
    private int id;
    private String nombre, apellido, dni, correo;

    // 1 constructor -> con todo


    public Usuario() {
    }

    public Usuario(int id, String nombre, String apellido, String dni, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s,%s",id,nombre,apellido,dni,correo);
    }
}
