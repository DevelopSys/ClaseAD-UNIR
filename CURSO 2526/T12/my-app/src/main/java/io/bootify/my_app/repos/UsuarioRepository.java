package io.bootify.my_app.repos;

import io.bootify.my_app.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findFirstByProductoidId(Long id);

    boolean existsByCorreoIgnoreCase(String correo);

    boolean existsByProductoidId(Long id);

}
