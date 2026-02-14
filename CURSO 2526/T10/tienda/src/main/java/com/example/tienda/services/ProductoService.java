package com.example.tienda.services;

import com.example.tienda.model.Producto;
import com.example.tienda.repository.ProductoRepository;
import com.example.tienda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;


    public List<Producto> getAll(){
        return productoRepository.findAll();
    }
    public Optional<Producto> getId(long id){
        return productoRepository.findById(id);
    }
}
