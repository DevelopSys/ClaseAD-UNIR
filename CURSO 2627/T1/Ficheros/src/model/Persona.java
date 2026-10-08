package model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


public class Persona implements Serializable {
    private String nombre;
    private String apellido;
    private int edad;
    private String mail;

    public Persona() {
    }

    public Persona(String nombre, String apellido, int edad, String mail) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.mail = mail;
    }

    public String toCSV() {
        return getNombre()+","+getApellido()+","+getEdad()+","+getMail();
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
