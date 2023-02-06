package com.app.crud.web.service;

import java.util.List;

import com.app.crud.beans.Estudiante;

public interface EstudianteService {

    public List<Estudiante> listarEstudiantes();

    public Estudiante crearEstudiante(Estudiante estudiante);

    public Estudiante obtenerEstudiantePorId(Long id);

    public Estudiante actualizarEstudiante(Estudiante estudiante);

    public void eliminarEstudiante(Long id);

    public List<Object> existeEstudiante(Estudiante estudiante);

}
