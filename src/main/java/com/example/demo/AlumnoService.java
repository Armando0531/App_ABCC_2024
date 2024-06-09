package com.example.demo;

import java.util.List;

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

}
