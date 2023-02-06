package com.app.crud.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.crud.beans.Estudiante;
import com.app.crud.web.service.EstudianteService;

@Controller
public class EstudianteController {

    @Autowired
    private EstudianteService servicio;

    @GetMapping({ "/estudiantes", "/" })
    public String listarEstudiantes(Model modelo) {
        modelo.addAttribute("estudiantes", servicio.listarEstudiantes());
        return "estudiantes";
    }

    @GetMapping("/estudiantes/crear")
    public String crearEstudiante(Model modelo) {
        Estudiante estudiante = new Estudiante();
        modelo.addAttribute("estudiante", estudiante);
        return "crear_estudiante";
    }

    @PostMapping("/estudiantes")
    public String guardarEstudiante(@Validated Estudiante estudiante, BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model modelo) {

        if (bindingResult.hasErrors()) {
            modelo.addAttribute("estudiante", estudiante);
            return "crear_estudiante";
        }

        List<Object> estudianteExisteObj = servicio.existeEstudiante(estudiante);
        if ((Boolean) estudianteExisteObj.get(0)) {
            modelo.addAttribute("emailUsado", estudianteExisteObj.get(1));
            return "crear_estudiante";
        }

        servicio.crearEstudiante(estudiante);
        redirectAttributes.addFlashAttribute("msgExito", "Estudiante creado con éxito");
        return "redirect:/estudiantes";
    }

    @GetMapping("/estudiantes/editar/{id}")
    public String editarEstudiante(@PathVariable(value = "id") Long id, Model modelo) {
        Estudiante estudiante = servicio.obtenerEstudiantePorId(id);
        modelo.addAttribute("estudiante", estudiante);
        return "editar_estudiante";
    }

    @PostMapping("/estudiantes/{id}")
    public String actualizarEstudiante(
            @PathVariable Long id, @Validated Estudiante estudiante, BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model modelo) {

        Estudiante estudianteExistente = servicio.obtenerEstudiantePorId(id);
        estudianteExistente.setId(id);
        estudianteExistente.setNombre(estudiante.getNombre());
        estudianteExistente.setApellido(estudiante.getApellido());
        estudianteExistente.setEmail(estudiante.getEmail());

        if (bindingResult.hasErrors()) {
            modelo.addAttribute("estudiante", estudiante);
            return "editar_estudiante";
        }

        servicio.actualizarEstudiante(estudianteExistente);
        redirectAttributes.addFlashAttribute("msgExito", "Estudiante actualizado con éxito");
        return "redirect:/estudiantes";
    }

    @GetMapping("/estudiantes/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable(value = "id") Long id) {
        servicio.eliminarEstudiante(id);
        return "redirect:/estudiantes";
    }
}
