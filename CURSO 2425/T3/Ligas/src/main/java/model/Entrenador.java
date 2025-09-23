package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "entrenadores")
public class Entrenador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nombre;
    @Column
    private int titulos;
    @Column
    private int calificacion;

    // bidireccinalidad
    @OneToOne(mappedBy = "entrenador")
    private Equipo equipo;



    public Entrenador(String nombre, int titulos, int calificacion) {
        this.nombre = nombre;
        this.titulos = titulos;
        this.calificacion = calificacion;
    }

    public Entrenador(String nombre) {
        this.nombre = nombre;
    }
}
