package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlumnoServiceImplement implements AlumnoService{
	
	private final AlumnoRepository alumnoRepository;
	
	@Autowired
    public AlumnoServiceImplement(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

	@Override
	public void guardarAlumno(Alumno alumno) {
		this.alumnoRepository.save(alumno);
	}
	
	@Override
	public void eliminarAlumno(String nc) {
		this.alumnoRepository.deleteById(nc);
	}
	
	@Override
    public void actualizarAlumno(Alumno alumno) {
        Alumno existente = alumnoRepository.findById(alumno.getNumControl()).orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        existente.setNombre(alumno.getNombre());
        alumnoRepository.save(existente);
    }

	@Override
	public List<Alumno> obtenerAlumnos() {
		// TODO Auto-generated method stub
		return alumnoRepository.findAll();
	}

	@Override
    public Alumno obtenerAlumno(String nc) {
        return alumnoRepository.findById(nc).orElseThrow(() -> new RuntimeException("No se encontró el alumno"));
    }
	
	public Optional<Alumno> buscarPorNumeroDeControl(String numControl) {
	    return alumnoRepository.findById(numControl);
	}

	
	
}
