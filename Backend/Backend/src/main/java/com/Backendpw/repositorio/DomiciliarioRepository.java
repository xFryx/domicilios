package com.Backendpw.repositorio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Backendpw.model.Domiciliario;


@Transactional
public interface DomiciliarioRepository extends JpaRepository<Domiciliario, Long> {
	//List<Domiciliario> findDomiciliarioByCelular(String telefono);
	List<Domiciliario> findByObjectId(Long idDomi);
}
