package com.universidad.controller;

import com.universidad.model.EvaluacionDocente;
import com.universidad.service.IEvaluacionDocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/api/evaluaciones-docente")
@Tag(name = "Evaluaciones Docente", description = "Controlador para gestionar evaluaciones de docentes")
public class EvaluacionDocenteController {
    @Autowired
    private IEvaluacionDocenteService evaluacionDocenteService;

    @PostMapping
    @Operation(summary = "Crear nueva evaluación docente", description = "Crea una nueva evaluación docente y devuelve el objeto creado")
    public ResponseEntity<EvaluacionDocente> crearEvaluacion(@RequestBody EvaluacionDocente evaluacion) {
        EvaluacionDocente nueva = evaluacionDocenteService.crearEvaluacion(evaluacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @GetMapping("/docente/{docenteId}")
    @Operation(summary = "Obtener evaluaciones por docente", description = "Devuelve una lista de evaluaciones para un docente específico")
    public ResponseEntity<List<EvaluacionDocente>> obtenerEvaluacionesPorDocente(@PathVariable Long docenteId) {
        List<EvaluacionDocente> evaluaciones = evaluacionDocenteService.obtenerEvaluacionesPorDocente(docenteId);
        return ResponseEntity.ok(evaluaciones);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener evaluación por ID", description = "Devuelve una evaluación específica por su ID")
    public ResponseEntity<EvaluacionDocente> obtenerEvaluacionPorId(@PathVariable Long id) {
        EvaluacionDocente evaluacion = evaluacionDocenteService.obtenerEvaluacionPorId(id);
        if (evaluacion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(evaluacion);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar evaluación docente", description = "Elimina una evaluación docente existente por su ID")
    public ResponseEntity<Void> eliminarEvaluacion(@PathVariable Long id) {
        evaluacionDocenteService.eliminarEvaluacion(id);
        return ResponseEntity.noContent().build();
    }
}
