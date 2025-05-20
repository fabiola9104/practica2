package com.universidad.controller;

import com.universidad.model.Materia;
import com.universidad.service.IMateriaService;

import jakarta.transaction.Transactional;

import com.universidad.dto.MateriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;


import java.util.List;

@RestController
@RequestMapping("/api/materias")
@Tag(name = "Materias", description = "Controlador para gestionar materias")
public class MateriaController {

    private final IMateriaService materiaService;
    private static final Logger logger = LoggerFactory.getLogger(MateriaController.class);

    @Autowired
    public MateriaController(IMateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @GetMapping
    @Operation(summary = "Obtener todas las materias", description = "Devuelve una lista de todas las materias")
    public ResponseEntity<List<MateriaDTO>> obtenerTodasLasMaterias() {
        long inicio = System.currentTimeMillis();
        logger.info("[MATERIA] Inicio obtenerTodasLasMaterias: {}", inicio);
        List<MateriaDTO> result = materiaService.obtenerTodasLasMaterias();
        long fin = System.currentTimeMillis();
        logger.info("[MATERIA] Fin obtenerTodasLasMaterias: {} (Duracion: {} ms)", fin, (fin-inicio));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener materia por ID", description = "Devuelve una materia específica por su ID")
    public ResponseEntity<MateriaDTO> obtenerMateriaPorId(@PathVariable Long id) {
        long inicio = System.currentTimeMillis();
        logger.info("[MATERIA] Inicio obtenerMateriaPorId: {}", inicio);
        MateriaDTO materia = materiaService.obtenerMateriaPorId(id);
        long fin = System.currentTimeMillis();
        logger.info("[MATERIA] Fin obtenerMateriaPorId: {} (Duracion: {} ms)", fin, (fin-inicio));
        if (materia == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(materia);
    }

    @GetMapping("/codigo/{codigoUnico}")
    @Operation(summary = "Obtener materia por código único", description = "Devuelve una materia específica por su código único")
    public ResponseEntity<MateriaDTO> obtenerMateriaPorCodigoUnico(@PathVariable String codigoUnico) {
        MateriaDTO materia = materiaService.obtenerMateriaPorCodigoUnico(codigoUnico);
        if (materia == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(materia);
    }

    @PostMapping
    @Operation(summary = "Crear nueva materia", description = "Crea una nueva materia")
    public ResponseEntity<MateriaDTO> crearMateria(@RequestBody MateriaDTO materia) {
        //MateriaDTO materiaDTO = new MateriaDTO(materia.getId(), materia.getNombre(), materia.getCodigoUnico());
        MateriaDTO nueva = materiaService.crearMateria(materia);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar materia", description = "Actualiza una materia existente")
    public ResponseEntity<MateriaDTO> actualizarMateria(@PathVariable Long id, @RequestBody MateriaDTO materia) {
        //MateriaDTO materiaDTO = new MateriaDTO(materia.getId(), materia.getNombreMateria(), materia.getCodigoUnico());
        MateriaDTO actualizadaDTO = materiaService.actualizarMateria(id, materia);
        //Materia actualizada = new Materia(actualizadaDTO.getId(), actualizadaDTO.getNombre(), actualizadaDTO.getCodigoUnico());
        return ResponseEntity.ok(actualizadaDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar materia", description = "Elimina una materia por su ID")
    public ResponseEntity<Void> eliminarMateria(@PathVariable Long id) {
        materiaService.eliminarMateria(id);
        return ResponseEntity.noContent().build();
    }

    // Asignar y desasignar materias a docentes
    @PostMapping("/{materiaId}/asignar-docente/{docenteId}")
    @Operation(summary = "Asignar docente a materia", description = "Asigna un docente a una materia")
    public ResponseEntity<Void> asignarDocente(@PathVariable Long materiaId, @PathVariable Long docenteId) {
        materiaService.asignarDocente(materiaId, docenteId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{materiaId}/desasignar-docente/{docenteId}")
    @Operation(summary = "Desasignar docente de materia", description = "Desasigna un docente de una materia")
    public ResponseEntity<Void> desasignarDocente(@PathVariable Long materiaId, @PathVariable Long docenteId) {
        materiaService.desasignarDocente(materiaId, docenteId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/formaria-circulo/{materiaId}/{prerequisitoId}") // Endpoint para verificar si una materia formaría un círculo con un prerequisito
    @Operation(summary = "Verificar si formaría un círculo", description = "Verifica si agregar un prerequisito formaría un círculo")
    @Transactional // Anotación que indica que este método debe ejecutarse dentro de una transacción
    public ResponseEntity<Boolean> formariaCirculo(@PathVariable Long materiaId, @PathVariable Long prerequisitoId) {
        MateriaDTO materiaDTO = materiaService.obtenerMateriaPorId(materiaId); // Obtiene la materia por su ID
        if (materiaDTO == null) { // Verifica si la materia existe
            return ResponseEntity.notFound().build();
        }
        Materia materia = new Materia(materiaDTO.getId(), materiaDTO.getNombreMateria(), materiaDTO.getCodigoUnico());
        // Crea una nueva instancia de Materia con los datos obtenidos
        // Verifica si agregar el prerequisito formaría un círculo
        boolean circulo = materia.formariaCirculo(prerequisitoId); // Llama al método formariaCirculo de la clase Materia
        if (circulo) { // Si formaría un círculo, retorna un error 400 Bad Request
            return ResponseEntity.badRequest().body(circulo);
        }
        return ResponseEntity.ok(circulo);
    }

}
