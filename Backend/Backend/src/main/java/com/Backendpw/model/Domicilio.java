package com.Backendpw.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Domicilios")
public class Domicilio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDomicilio;
	
	@Column
	private String estado;
	@Column
	private String nombreDes;
	@Column
	private String dirDes;
	@Column
	private String coorDes;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaDes;
	@Column
	private String dirSalida;
	@Column
	private String coorSalida;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaSalida;
	@Column
	private String telefonoDes;
	@Column
	private String observacion;
	@Column
	private Long id_Domiciliario;
	@Column
	private Long id_Solicitante;
	
	public Long getId_Solicitante() {
		return id_Solicitante;
	}


	public void setId_Solicitante(Long id_Solicitante) {
		this.id_Solicitante = id_Solicitante;
	}


	public Long getId_Domiciliario() {
		return id_Domiciliario;
	}


	public void setId_Domiciliario(Long id_Domiciliario) {
		this.id_Domiciliario = id_Domiciliario;
	}


	public Long getIdDomicilio() {
		return idDomicilio;
	}


	public void setIdDomicilio(Long idDomicilio) {
		this.idDomicilio = idDomicilio;
	}


	public String getTelefonoDes() {
		return telefonoDes;
	}


	public void setTelefonoDes(String telefonoDes) {
		this.telefonoDes = telefonoDes;
	}


	public String getObservacion() {
		return observacion;
	}


	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}


	public Domiciliario getDomiciliario() {
		return domiciliario;
	}


	public void setDomiciliario(Domiciliario domiciliario) {
		this.domiciliario = domiciliario;
	}


	public Solicitante getSolicitante() {
		return solicitante;
	}


	public void setSolicitante(Solicitante solicitante) {
		this.solicitante = solicitante;
	}
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="idDomi")
	private Domiciliario domiciliario;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="idSol")
	private Solicitante solicitante;
	
	
	
	public Domicilio(Long idDomicilio, String estado, String nombreDes, String dirDes, String coorDes, Date horaDes,
			String dirSalida, String coorSalida, Date horaSalida, String telefonoDes, String observacion,
			Domiciliario domiciliario, Solicitante solicitante) {
		super();
		this.idDomicilio = idDomicilio;
		this.estado = estado;
		this.nombreDes = nombreDes;
		this.dirDes = dirDes;
		this.coorDes = coorDes;
		this.horaDes = horaDes;
		this.dirSalida = dirSalida;
		this.coorSalida = coorSalida;
		this.horaSalida = horaSalida;
		this.telefonoDes = telefonoDes;
		this.observacion = observacion;
		this.id_Domiciliario = domiciliario.getId();
		this.id_Solicitante = solicitante.getId();
		this.domiciliario = domiciliario;
		this.solicitante = solicitante;
	}


	public Domicilio() {
		super();
	}
	
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getNombreDes() {
		return nombreDes;
	}

	public void setNombreDes(String nombreDes) {
		this.nombreDes = nombreDes;
	}

	public String getDirDes() {
		return dirDes;
	}

	public void setDirDes(String dirDes) {
		this.dirDes = dirDes;
	}

	public String getCoorDes() {
		return coorDes;
	}

	public void setCoorDes(String coorDes) {
		this.coorDes = coorDes;
	}

	public Date getHoraDes() {
		return horaDes;
	}

	public void setHoraDes(Date horaDes) {
		this.horaDes = horaDes;
	}

	public String getDirSalida() {
		return dirSalida;
	}

	public void setDirSalida(String dirSalida) {
		this.dirSalida = dirSalida;
	}

	public String getCoorSalida() {
		return coorSalida;
	}

	public void setCoorSalida(String coorSalida) {
		this.coorSalida = coorSalida;
	}

	public Date getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(Date horaSalida) {
		this.horaSalida = horaSalida;
	}

	
	
	
}
