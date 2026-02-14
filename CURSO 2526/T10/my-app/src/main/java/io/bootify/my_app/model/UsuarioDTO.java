package io.bootify.my_app.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UsuarioDTO {

    private Long id;

    @Size(max = 255)
    private String nombre;

    @Size(max = 255)
    private String pass;

    @Size(max = 255)
    @UsuarioCorreoUnique
    private String correo;

    @UsuarioProductoidUnique
    private Long productoid;

}
