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
	    model.addAttribute("alumno", new Alumno());  // Añade un objeto alumno vacío para el formulario
	    return "formulario_agregar";  // El nombre de la vista del formulario
	}
	
	@PostMapping("/guardarNuevo")
    public String guardarAlumno(@ModelAttribute("alumno") Alumno alumno, RedirectAttributes redirectAttributes) {
        Optional<Alumno> alumnoExistente = alumnoService.buscarPorNumeroDeControl(alumno.getNumControl());
        if (alumnoExistente.isPresent()) {
            redirectAttributes.addFlashAttribute("errorMessage", "No se puede crear un nuevo alumno porque ya existe alguien con ese número de control.");
            return "redirect:/agregar";
        } else {
            alumnoService.guardarAlumno(alumno);  // Asegúrate de que esto llama a un método en el servicio que a su vez llama a `save` en el repositorio
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
	    return "redirect:/";  // Asegúrate de redirigir a la vista adecuada después de la operación
	}
	
	@GetMapping("/editar/{id}")
	public String editar(Model modelo, @PathVariable String id) {
		Alumno alumno = alumnoService.obtenerAlumno(id);
		modelo.addAttribute("alumno", alumno);
		return "form";
	}
}
