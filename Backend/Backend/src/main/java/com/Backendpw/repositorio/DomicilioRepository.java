package com.Backendpw.repositorio;



import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Backendpw.model.Domicilio;

@Transactional
public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {
	//save- guardar y actualizar
	//FindOne o GetOne - busca por Id
	//FindById
	//FindAll
	//count
	//delete
	//deleteById
	//List<Domicilio> findByDomiciliarioId(Long idDomi);
	//List<Domicilio> findBySolicitanteId(Long idSol);
	//List<Domicilio> findByEstado(String estado);
}
