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
@Table(name="Domiciliarios")
public class Domiciliario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long objectId;
	@Column
	private String nombre;
	@Column
	private Integer celular;
	@Column
	private String direccion;
	@Column
	private String placa_moto;
	@Column
	private Integer cedula;
	
	
	
	public Long getObjectId() {
		return objectId;
	}



	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public String getplaca_moto() {
		return placa_moto;
	}



	public void setplaca_moto(String placa_moto) {
		this.placa_moto = placa_moto;
	}



	public Integer getCedula() {
		return cedula;
	}



	public void setCedula(Integer cedula) {
		this.cedula = cedula;
	}
	@OneToMany(mappedBy="domiciliario", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<Domicilio> domicilio;
	
	
	public Domiciliario() {
		super();
	}
	
	
	
	public Domiciliario(Long objectId, String nombre, Integer celular, String placa_moto, String direccion) {
		super();
		this.objectId = objectId;
		this.nombre = nombre;
		this.celular = celular;
		this.placa_moto = placa_moto;
		this.direccion = direccion;
	}



	public Long getId() {
		return objectId;
	}
	public void setId(Long objectId) {
		this.objectId = objectId;
	}
	public String getNombre() {
		return nombre;
	}

	public Set<Domicilio> getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Set<Domicilio> domicilio) {
		this.domicilio = domicilio;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getCelular() {
		return celular;
	}
	public void setCelular(Integer celular) {
		this.celular = celular;
	}
}
