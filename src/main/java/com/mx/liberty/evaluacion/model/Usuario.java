package com.mx.liberty.evaluacion.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario", schema = "evaluacion")
public class Usuario implements Serializable {

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "nombre", nullable = false, length = 255)
	private String nombre;
	@Column(name = "email", nullable = false)
	private String email;
	@Column(name = "genero", nullable = false, length = 255)
	private String genero;
	@Column(name = "estatus")
	private boolean estatus;
	@Column(name = "foto_usuario", nullable = true)
	private String fotoUsuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public boolean isEstatus() {
		return estatus;
	}

	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}

	public String getFotoUsuario() {
		return fotoUsuario;
	}

	public void setFotoUsuario(String fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", email=" + email + ", genero=" + genero + ", estatus="
				+ estatus + ", fotoUsuario=" + fotoUsuario + "]";
	}

}
