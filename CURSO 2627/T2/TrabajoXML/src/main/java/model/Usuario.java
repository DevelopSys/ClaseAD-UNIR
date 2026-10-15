package model;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Usuario {

    private String nombre, apellido, correo, dni;
    private int telefono;

    public Usuario(String nombre, String apellido, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }


}
