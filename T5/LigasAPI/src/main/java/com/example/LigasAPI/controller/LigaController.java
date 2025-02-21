package com.example.LigasAPI.controller;

// se utiliza como punto de entrada de las peticiones REST

import com.example.LigasAPI.model.Liga;
import com.example.LigasAPI.service.LigaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("ligas")
public class LigaController {

    @Autowired
    private LigaService ligaService;
    // todos los metodos que quiero que se puedan ejecutar desde la URL
    @GetMapping("error")
    public String getError(){
        return "Error en la app";
    }

    // TODO pendiente de agregar de forma params
    @PostMapping("add")
    public String addLiga(@RequestBody Liga liga){
        ligaService.agregarLiga(liga);
        return "Liga agregada correctamente con nombre "+liga.getNombre();
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Liga>> getLigas(){
        return new ResponseEntity<>(ligaService.getAllLigas(),HttpStatus.OK);
    }

}
