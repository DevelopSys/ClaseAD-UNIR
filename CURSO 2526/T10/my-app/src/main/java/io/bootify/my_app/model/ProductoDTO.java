package io.bootify.my_app.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductoDTO {

    private Long id;

    @Size(max = 255)
    private String nombre;

    private Double precio;

}
