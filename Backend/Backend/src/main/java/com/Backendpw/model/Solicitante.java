package com.Backendpw.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Solicitantes")
public class Solicitante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSol;
	@Column
	private String nombre;
	@Column
	private String telefono;
	
	public Long getIdSol() {
		return idSol;
	}

	public void setIdSol(Long idSol) {
		this.idSol = idSol;
	}

	public Set<Domicilio> getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Set<Domicilio> domicilio) {
		this.domicilio = domicilio;
	}
	@Column
	private String coordDir;
	
	@OneToMany(mappedBy="solicitante", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<Domicilio> domicilio;
	
	
	public Solicitante() {
		super();
	}
	
	public Long getId() {
		return idSol;
	}
	public void setId(Long idSol) {
		this.idSol = idSol;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	

}
