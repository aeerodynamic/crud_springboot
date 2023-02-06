package com.app.crud.web.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.crud.beans.Estudiante;
import com.app.crud.web.repository.EstudianteRepository;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteRepository repositorio;

    @Override
    public List<Estudiante> listarEstudiantes() {
        return repositorio.findAll();
    }

    @Override
    public Estudiante crearEstudiante(Estudiante estudiante) {
        return repositorio.save(estudiante);
    }

    @Override
    public Estudiante obtenerEstudiantePorId(Long id) {
        return repositorio.findById(id).get();
    }

    @Override
    public Estudiante actualizarEstudiante(Estudiante estudiante) {
        return repositorio.save(estudiante);
    }

    @Override
    public void eliminarEstudiante(Long id) {
        repositorio.deleteById(id);
    }

    @Override
    public List<Object> existeEstudiante(Estudiante estudiante) {
        boolean userExists = false;
        String message = null;
        Optional<Estudiante> existeEstudianteEmail = repositorio.findByEmail(estudiante.getEmail());
        if (existeEstudianteEmail.isPresent()) {
            userExists = true;
            message = "El email ya está en uso";
        }

        return Arrays.asList(userExists, message);
    }

}
