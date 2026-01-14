package io.bootify.my_app.service;

import io.bootify.my_app.domain.Producto;
import io.bootify.my_app.events.BeforeDeleteProducto;
import io.bootify.my_app.model.ProductoDTO;
import io.bootify.my_app.repos.ProductoRepository;
import io.bootify.my_app.util.CustomCollectors;
import io.bootify.my_app.util.NotFoundException;
import java.util.List;
import java.util.Map;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final ApplicationEventPublisher publisher;

    public ProductoService(final ProductoRepository productoRepository,
            final ApplicationEventPublisher publisher) {
        this.productoRepository = productoRepository;
        this.publisher = publisher;
    }

    public List<ProductoDTO> findAll() {
        final List<Producto> productoes = productoRepository.findAll(Sort.by("id"));
        return productoes.stream()
                .map(producto -> mapToDTO(producto, new ProductoDTO()))
                .toList();
    }

    public ProductoDTO get(final Long id) {
        return productoRepository.findById(id)
                .map(producto -> mapToDTO(producto, new ProductoDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ProductoDTO productoDTO) {
        final Producto producto = new Producto();
        mapToEntity(productoDTO, producto);
        return productoRepository.save(producto).getId();
    }

    public void update(final Long id, final ProductoDTO productoDTO) {
        final Producto producto = productoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(productoDTO, producto);
        productoRepository.save(producto);
    }

    public void delete(final Long id) {
        final Producto producto = productoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        publisher.publishEvent(new BeforeDeleteProducto(id));
        productoRepository.delete(producto);
    }

    private ProductoDTO mapToDTO(final Producto producto, final ProductoDTO productoDTO) {
        productoDTO.setId(producto.getId());
        productoDTO.setNombre(producto.getNombre());
        productoDTO.setPrecio(producto.getPrecio());
        return productoDTO;
    }

    private Producto mapToEntity(final ProductoDTO productoDTO, final Producto producto) {
        producto.setNombre(productoDTO.getNombre());
        producto.setPrecio(productoDTO.getPrecio());
        return producto;
    }

    public Map<Long, Long> getProductoValues() {
        return productoRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Producto::getId, Producto::getId));
    }

}
