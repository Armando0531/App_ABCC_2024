package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table(name="Alumnos")
public class Alumno {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Num_Control")
	private String numControl;
	
	@Column(name="nombre")
	private String nombre;

	public String getNumControl() {
		return numControl;
	}

	public void setNumControl(String numControl) {
		this.numControl = numControl;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
