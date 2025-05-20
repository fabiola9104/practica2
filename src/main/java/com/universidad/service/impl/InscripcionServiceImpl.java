package com.universidad.service.impl;

import com.universidad.model.Inscripcion;
import com.universidad.model.Estudiante;
import com.universidad.model.Materia;
import com.universidad.dto.InscripcionDTO;
import com.universidad.repository.InscripcionRepository;
import com.universidad.repository.EstudianteRepository;
import com.universidad.repository.MateriaRepository;
import com.universidad.service.IInscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InscripcionServiceImpl implements IInscripcionService {
    @Autowired
    private InscripcionRepository inscripcionRepository;
    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private MateriaRepository materiaRepository;

    private InscripcionDTO mapToDTO(Inscripcion inscripcion) {
        return InscripcionDTO.builder()
                .id(inscripcion.getId())
                .estudianteId(inscripcion.getEstudiante().getId())
                .materiaId(inscripcion.getMateria().getId())
                .fechaInscripcion(inscripcion.getFechaInscripcion())
                .build();
    }

    @Override
    public List<InscripcionDTO> obtenerTodas() {
        return inscripcionRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public InscripcionDTO obtenerPorId(Long id) {
        Inscripcion insc = inscripcionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Inscripción no encontrada"));
        return mapToDTO(insc);
    }

    @Override
    public InscripcionDTO crear(InscripcionDTO dto) {
        if (inscripcionRepository.existsByEstudianteIdAndMateriaId(dto.getEstudianteId(), dto.getMateriaId())) {
            throw new IllegalArgumentException("Ya existe una inscripción para este estudiante y materia");
        }
        Estudiante estudiante = estudianteRepository.findById(dto.getEstudianteId()).orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado"));
        Materia materia = materiaRepository.findById(dto.getMateriaId()).orElseThrow(() -> new EntityNotFoundException("Materia no encontrada"));
        Inscripcion insc = Inscripcion.builder()
                .estudiante(estudiante)
                .materia(materia)
                .fechaInscripcion(dto.getFechaInscripcion())
                .build();
        return mapToDTO(inscripcionRepository.save(insc));
    }

    @Override
    public InscripcionDTO actualizar(Long id, InscripcionDTO dto) {
        Inscripcion insc = inscripcionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Inscripción no encontrada"));
        insc.setFechaInscripcion(dto.getFechaInscripcion());
        // Opcionalmente permite cambiar estudiante o materia aquí
        return mapToDTO(inscripcionRepository.save(insc));
    }

    @Override
    public void eliminar(Long id) {
        inscripcionRepository.deleteById(id);
    }
}
