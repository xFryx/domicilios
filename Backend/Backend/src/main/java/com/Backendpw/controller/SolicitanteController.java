package com.Backendpw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Backendpw.model.Solicitante;
import com.Backendpw.repositorio.SolicitanteRepository;

@CrossOrigin(origins= {"http://localhost:8080", "http://localhost:8081"})
@RestController
@RequestMapping("/api/solicitante")
public class SolicitanteController {
	@Autowired	
	private SolicitanteRepository sRepository;
	
	@GetMapping("/Solicitantes")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<Solicitante>> getAllSolicitantes(){
		List<Solicitante> solicitantes=sRepository.findAll();
		return new ResponseEntity<>(solicitantes,HttpStatus.OK);
	}
	
	@GetMapping("/Solicitantes/{idSol}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Solicitante> getSolicitanteById(@PathVariable("idSol") Long idSol){
		
		Solicitante s = sRepository.findById(idSol).get();
		if(s != null) {
			return new ResponseEntity<>(s,HttpStatus.OK);	
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/Solicitantes")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Solicitante> createSolicitante(@RequestBody Solicitante solicitante){
		try {
			Solicitante s = sRepository.save(solicitante);
			return new ResponseEntity<>(s,HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/Solicitante/{idSol}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Solicitante> updateSolicitante(@PathVariable("idSol") Long idSol, @RequestBody Solicitante datosSolicitante){
		Solicitante s= sRepository.findById(idSol).get();
		if(s != null) {
			s.setNombre(datosSolicitante.getNombre());
			s.setTelefono(datosSolicitante.getTelefono());
			Solicitante solicitanteUpdated = sRepository.save(s);
			return new ResponseEntity<>(solicitanteUpdated,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}	
	
	@DeleteMapping("/Solicitante/{idSol}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> delete(@PathVariable("idSol") Long id) {
		try {
			sRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	

}
