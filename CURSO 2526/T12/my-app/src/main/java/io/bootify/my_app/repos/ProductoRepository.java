package io.bootify.my_app.repos;

import io.bootify.my_app.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
