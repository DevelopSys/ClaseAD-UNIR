package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table (name = "ligas")
public class Liga implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nombre;
    @Column
    private Date fecha_inicio;
    @Column(name = "fecha_fin")
    private Date fecha_final;
    // bidireccionalidad
    @OneToMany(mappedBy = "liga")
    private List<Equipo> equipos;

    public Liga(String nombre) {
        this.nombre = nombre;
    }

    public Liga(String nombre, Date fecha_inicio, Date fecha_final) {
        this.nombre = nombre;
        this.fecha_inicio = fecha_inicio;
        this.fecha_final = fecha_final;
    }
}
