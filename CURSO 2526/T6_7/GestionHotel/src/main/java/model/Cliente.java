package model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@ToString(exclude = "trabajadores")
@EqualsAndHashCode(exclude = "trabajadores")
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "reservas",
            joinColumns = @JoinColumn(name = "id_cliente"),
            inverseJoinColumns = @JoinColumn(name = "id_trabajador"))
    private Set<Trabajador> trabajadores = new HashSet<>();


    public Cliente(String nombre) {
        this.nombre = nombre;
    }

    public void addTrabajador(Trabajador trabajador){
        this.trabajadores.add(trabajador);
        trabajador.getClientes().add(this);
    }

}
