package model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NamedQuery;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

// TODO cuidado con el toString
@Data
@ToString(exclude = {"perfil","clientes"})
@EqualsAndHashCode(exclude = {"perfil","clientes"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "empleados")
@NamedQuery(name = "Trabajador.getByEmail", query = "FROM Trabajador t WHERE t.correo=:email")
@NamedQuery(name = "Trabajador.getAll", query = "FROM Trabajador")
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
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_perfil")
    private Perfil perfil;
    @ManyToMany(mappedBy = "trabajadores")
    private Set<Cliente> clientes = new HashSet<>();


    public Trabajador(String nombre, String apellido, int antiguedad, String correo, int salario, Direccion direccion, Perfil perfil) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.antiguedad = antiguedad;
        this.correo = correo;
        this.salario = salario;
        this.direccion = direccion;
        this.perfil = perfil;
    }

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

    public void mostrarDatos() {
        System.out.println("id = " + id);
        System.out.println("nombre = " + nombre);
        System.out.println("apellido = " + apellido);
        System.out.println("antiguedad = " + antiguedad);
        System.out.println("correo = " + correo);
    }
}
