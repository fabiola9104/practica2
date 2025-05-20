package com.universidad.service;

import com.universidad.dto.InscripcionDTO;
import java.util.List;

public interface IInscripcionService {
    List<InscripcionDTO> obtenerTodas();
    InscripcionDTO obtenerPorId(Long id);
    InscripcionDTO crear(InscripcionDTO dto);
    InscripcionDTO actualizar(Long id, InscripcionDTO dto);
    void eliminar(Long id);
}
