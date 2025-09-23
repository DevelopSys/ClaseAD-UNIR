package com.example.LigasAPI.service;

import com.example.LigasAPI.model.Liga;
import com.example.LigasAPI.repository.LigaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LigaServiceImp implements LigaService {

    @Autowired
    private LigaRepository ligaRepository;

    @Override
    public Liga agregarLiga(Liga liga) {
        // quiero que se pueda agregar la liga,
        // pero si no existe previamente una liga con ese nombre
        // repository para poder ejecutar los metodos contra BD
        // if (ligaRepository.findByNombre("Calcio")==null){
        // } else {
        //    return null;
        // }

        return ligaRepository.save(liga);

    }

    @Override
    public List<Liga> getAllLigas() {
        return ligaRepository.findAll();
    }

}
