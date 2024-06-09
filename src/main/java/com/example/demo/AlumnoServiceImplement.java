package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlumnoServiceImplement implements AlumnoService{
	
	@Autowired
	private AlumnoRepository alumnoRepository;

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
		//this.alumnoRepository.update(alumno);
		
	}

	@Override
	public List<Alumno> obtenerAlumnos() {
		// TODO Auto-generated method stub
		return alumnoRepository.findAll();
	}

	@Override
	public Alumno obtenerAlumno(String nc) {
		
		Optional<Alumno> o = alumnoRepository.findById(nc);
		
		if(o.isPresent())
			return o.get();
		else
			throw new RuntimeException("No se encontro el alumno");
		
	}
	
	
	
}
