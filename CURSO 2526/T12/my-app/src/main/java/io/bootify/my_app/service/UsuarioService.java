package io.bootify.my_app.service;

import io.bootify.my_app.domain.Producto;
import io.bootify.my_app.domain.Usuario;
import io.bootify.my_app.events.BeforeDeleteProducto;
import io.bootify.my_app.model.UsuarioDTO;
import io.bootify.my_app.repos.ProductoRepository;
import io.bootify.my_app.repos.UsuarioRepository;
import io.bootify.my_app.util.NotFoundException;
import io.bootify.my_app.util.ReferencedException;
import java.util.List;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;

    public UsuarioService(final UsuarioRepository usuarioRepository,
            final ProductoRepository productoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
    }

    public List<UsuarioDTO> findAll() {
        final List<Usuario> usuarios = usuarioRepository.findAll(Sort.by("id"));
        return usuarios.stream()
                .map(usuario -> mapToDTO(usuario, new UsuarioDTO()))
                .toList();
    }

    public UsuarioDTO get(final Long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> mapToDTO(usuario, new UsuarioDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final UsuarioDTO usuarioDTO) {
        final Usuario usuario = new Usuario();
        mapToEntity(usuarioDTO, usuario);
        return usuarioRepository.save(usuario).getId();
    }

    public void update(final Long id, final UsuarioDTO usuarioDTO) {
        final Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(usuarioDTO, usuario);
        usuarioRepository.save(usuario);
    }

    public void delete(final Long id) {
        final Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        usuarioRepository.delete(usuario);
    }

    private UsuarioDTO mapToDTO(final Usuario usuario, final UsuarioDTO usuarioDTO) {
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setPass(usuario.getPass());
        usuarioDTO.setCorreo(usuario.getCorreo());
        usuarioDTO.setProductoid(usuario.getProductoid() == null ? null : usuario.getProductoid().getId());
        return usuarioDTO;
    }

    private Usuario mapToEntity(final UsuarioDTO usuarioDTO, final Usuario usuario) {
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setPass(usuarioDTO.getPass());
        usuario.setCorreo(usuarioDTO.getCorreo());
        final Producto productoid = usuarioDTO.getProductoid() == null ? null : productoRepository.findById(usuarioDTO.getProductoid())
                .orElseThrow(() -> new NotFoundException("productoid not found"));
        usuario.setProductoid(productoid);
        return usuario;
    }

    public boolean correoExists(final String correo) {
        return usuarioRepository.existsByCorreoIgnoreCase(correo);
    }

    public boolean productoidExists(final Long id) {
        return usuarioRepository.existsByProductoidId(id);
    }

    @EventListener(BeforeDeleteProducto.class)
    public void on(final BeforeDeleteProducto event) {
        final ReferencedException referencedException = new ReferencedException();
        final Usuario productoidUsuario = usuarioRepository.findFirstByProductoidId(event.getId());
        if (productoidUsuario != null) {
            referencedException.setKey("producto.usuario.productoid.referenced");
            referencedException.addParam(productoidUsuario.getId());
            throw referencedException;
        }
    }

}
