package com.universidad.controller;

import com.universidad.dto.InscripcionDTO;
import com.universidad.service.IInscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;


import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
@Tag(name = "Inscripciones", description = "Controlador para gestionar inscripciones")
public class InscripcionController {

    @Autowired
    private IInscripcionService inscripcionService;

    @GetMapping
    @Operation(summary = "Obtener todas las inscripciones", description = "Devuelve una lista de todas las inscripciones")
    public ResponseEntity<List<InscripcionDTO>> listar() {
        return ResponseEntity.ok(inscripcionService.obtenerTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener inscripción por ID", description = "Devuelve una inscripción específica por su ID")
    public ResponseEntity<InscripcionDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(inscripcionService.obtenerPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear nueva inscripción", description = "Crea una nueva inscripción y devuelve el objeto creado")
    public ResponseEntity<InscripcionDTO> crear(@Valid @RequestBody InscripcionDTO dto) {
        return ResponseEntity.ok(inscripcionService.crear(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar inscripción", description = "Actualiza una inscripción existente y devuelve el objeto actualizado")
    public ResponseEntity<InscripcionDTO> actualizar(@PathVariable Long id, @Valid @RequestBody InscripcionDTO dto) {
        return ResponseEntity.ok(inscripcionService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar inscripción", description = "Elimina una inscripción existente por su ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        inscripcionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
