package com.example.demo;

import java.util.List;
import java.util.Optional;

public interface AlumnoService {
	
	//altas
	void guardarAlumno(Alumno alumno);
	//bajas
	void eliminarAlumno(String nc);
	//cambios
	void actualizarAlumno(Alumno alumno);
	//consultas
	List<Alumno> obtenerAlumnos();
	Alumno obtenerAlumno(String nc);
	
	Optional<Alumno> buscarPorNumeroDeControl(String numControl);


}
