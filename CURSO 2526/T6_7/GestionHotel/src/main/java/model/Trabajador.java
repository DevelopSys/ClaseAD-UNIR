package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// TODO cuidado con el toString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "empleados")
public class Trabajador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String apellido;
    private int antiguedad;
    @Column(name = "mail", unique = true)
    private String correo;
    private int salario;
    @Embedded
    private Direccion direccion;
    @Transient
    private String comentarios;
    @Transient
    private boolean disponibilidad;

    public Trabajador(String nombre, String apellido, int antiguedad, String correo, int salario, Direccion direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.antiguedad = antiguedad;
        this.correo = correo;
        this.salario = salario;
        this.direccion = direccion;
    }

    public Trabajador(String nombre, String apellido, int antiguedad, String correo, int salario, Direccion direccion, String comentarios, boolean disponibilidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.antiguedad = antiguedad;
        this.correo = correo;
        this.salario = salario;
        this.direccion = direccion;
        this.comentarios = comentarios;
        this.disponibilidad = disponibilidad;
    }

    public Trabajador(String nombre, String apellido, int antiguedad, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.antiguedad = antiguedad;
        this.correo = correo;
    }

    public Trabajador(String nombre, String apellido, int antiguedad, String correo, String comentarios, boolean disponibilidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.antiguedad = antiguedad;
        this.correo = correo;
        this.comentarios = comentarios;
        this.disponibilidad = disponibilidad;
    }
}
