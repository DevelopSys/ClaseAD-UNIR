package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nombre;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_habitacion")
    private Habitacion habitacion;

    @ManyToMany()
    @JoinTable(name = "reservas",
            joinColumns = @JoinColumn(name = "id_cliente")
            ,inverseJoinColumns = @JoinColumn(name = "id_trabajador"))
    private List<Trabajador> listaTrabajadores;

    public Cliente(String nombre) {
        this.nombre = nombre;
    }
}
