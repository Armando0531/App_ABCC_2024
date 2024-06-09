package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AlumnoController {
	
	@Autowired
	private AlumnoService alumnoService;
	
	@GetMapping("/")
	public String paginaInicio(Model model) {
		model.addAttribute("listaAlumnos", alumnoService.obtenerAlumnos());
		return "index";
	}

	@GetMapping("/agregar")
	public String mostrarFormularioDeAgregar(Model model) {
	    model.addAttribute("alumno", new Alumno());  
	    return "formulario_agregar";  
	}
	
	@PostMapping("/guardarNuevo")
    public String guardarAlumno(@ModelAttribute("alumno") Alumno alumno, RedirectAttributes redirectAttributes) {
        Optional<Alumno> alumnoExistente = alumnoService.buscarPorNumeroDeControl(alumno.getNumControl());
        if (alumnoExistente.isPresent()) {
            redirectAttributes.addFlashAttribute("errorMessage", "No se puede crear un nuevo alumno porque ya existe alguien con ese número de control.");
            return "redirect:/agregar";
        } else {
            alumnoService.guardarAlumno(alumno);  
            return "redirect:/";
        }
    }

	
	@PostMapping(path="/actualizar")
	public String guardar(@Validated Alumno a, Model model) {
		alumnoService.guardarAlumno(a);
		return "redirect:/";
	}
	
	@GetMapping("/eliminar/{numControl}")
	public String eliminarAlumno(@PathVariable String numControl, RedirectAttributes redirectAttributes) {
	    try {
	        alumnoService.eliminarAlumno(numControl);
	        redirectAttributes.addFlashAttribute("successMessage", "Alumno eliminado correctamente.");
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar el alumno.");
	    }
	    return "redirect:/";  
	}
	
	@GetMapping("/editar/{numControl}")
	public String editarAlumno(@PathVariable String numControl, Model model) {
	    Alumno alumno = alumnoService.obtenerAlumno(numControl);
	    if (alumno != null) {
	        model.addAttribute("alumno", alumno);
	        return "formulario_editar";  
	    } else {
	        return "redirect:/"; 
	    }
	}
	
	@PostMapping("/actualizar2")
	public String actualizarAlumno(@ModelAttribute("alumno") Alumno alumno, RedirectAttributes redirectAttributes) {
	    try {
	        alumnoService.actualizarAlumno(alumno);
	        redirectAttributes.addFlashAttribute("successMessage", "Alumno actualizado con éxito.");
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("errorMessage", "Error al actualizar el alumno.");
	    }
	    return "redirect:/";
	}

}
