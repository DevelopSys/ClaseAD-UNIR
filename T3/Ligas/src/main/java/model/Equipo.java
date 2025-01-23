package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "equipos")
public class Equipo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nombre;
    @Column
    private String ciudad;
    // unidireccionamente
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_entrenador")
    private Entrenador entrenador;

    // bidireccionalidad
    @OneToMany(mappedBy = "equipo", fetch = FetchType.EAGER)
    private List<Jugador> jugadores;

    // unidireccional
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_liga")
    private Liga liga;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "inscripciones", joinColumns = @JoinColumn(name = "id_equipo")
            , inverseJoinColumns = @JoinColumn(name = "id_competicion"))
    private List<Competicion> competiciones;

    public Equipo(String nombre, String ciudad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
    }
}
