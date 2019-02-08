package com.acuevas.vetstucom.model;

// default package
// Generated 07-feb-2019 22:47:16 by Hibernate Tools 5.3.6.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Usuarios generated by hbm2java
 */
public class Usuarios implements java.io.Serializable {

	private Integer id;
	private String nombre;
	private String apellidos;
	private String dni;
	private String matricula;
	private String pass;
	private Integer tipoUsuario;
	private Date ultimoAcceso;
	private Set expedienteses = new HashSet(0);

	public Usuarios() {
	}

	public Usuarios(String nombre, String apellidos, String dni, String matricula, String pass, Integer tipoUsuario,
			Date ultimoAcceso, Set expedienteses) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.matricula = matricula;
		this.pass = pass;
		this.tipoUsuario = tipoUsuario;
		this.ultimoAcceso = ultimoAcceso;
		this.expedienteses = expedienteses;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Integer getTipoUsuario() {
		return this.tipoUsuario;
	}

	public void setTipoUsuario(Integer tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Date getUltimoAcceso() {
		return this.ultimoAcceso;
	}

	public void setUltimoAcceso(Date ultimoAcceso) {
		this.ultimoAcceso = ultimoAcceso;
	}

	public Set getExpedienteses() {
		return this.expedienteses;
	}

	public void setExpedienteses(Set expedienteses) {
		this.expedienteses = expedienteses;
	}

}