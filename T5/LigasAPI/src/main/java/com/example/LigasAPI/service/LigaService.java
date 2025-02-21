package com.example.LigasAPI.service;

import com.example.LigasAPI.model.Liga;

import java.util.List;

public interface LigaService {
    // escribo todos los metodos que quiero llamar desde el controller. Solo se escribe la firma
    // aportan la logica del negocio
    // Liga updateLigaFecha(LocalDate localDate)
    Liga agregarLiga(Liga liga);

    List<Liga> getAllLigas();
}
