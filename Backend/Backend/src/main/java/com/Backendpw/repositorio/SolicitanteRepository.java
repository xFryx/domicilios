package com.Backendpw.repositorio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Backendpw.model.Solicitante;


@Transactional
public interface SolicitanteRepository extends JpaRepository<Solicitante, Long> {
	List<Solicitante> findSolicitanteByNombre(String nombre);

}
