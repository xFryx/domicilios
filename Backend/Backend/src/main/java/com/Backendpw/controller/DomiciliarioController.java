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

import com.Backendpw.model.Domiciliario;
import com.Backendpw.repositorio.DomiciliarioRepository;

@CrossOrigin(origins= {"http://localhost:8080", "http://localhost:8081"})
@RestController
@RequestMapping("/api/domiciliario")
public class DomiciliarioController {
	@Autowired	
	private DomiciliarioRepository dRepository;
	
	@GetMapping("/domiciliarios")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<Domiciliario>> getAllDomiciliarios(){
		List<Domiciliario> domiciliarios=dRepository.findAll();
		return new ResponseEntity<>(domiciliarios,HttpStatus.OK);
	}
	
	@GetMapping("/domiciliarios/{objectId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Domiciliario> getDomiciliarioById(@PathVariable("objectId") Long objectId){
		
		Domiciliario d = dRepository.findById(objectId).get();
		if(d != null) {
			return new ResponseEntity<>(d,HttpStatus.OK);	
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/domiciliarios")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Domiciliario> createDomiciliario(@RequestBody Domiciliario domiciliario){
		try {
			Domiciliario d = dRepository.save(domiciliario);
			return new ResponseEntity<>(d,HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/domiciliarios/{objectId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Domiciliario> updateDomiciliario(@PathVariable("objectId") Long objectId, @RequestBody Domiciliario datosDomiciliario){
		Domiciliario d= dRepository.findById(objectId).get();
		if(d != null) {
			d.setNombre(datosDomiciliario.getNombre());
			d.setCelular(datosDomiciliario.getCelular());
			Domiciliario domiciliarioUpdated = dRepository.save(d);
			return new ResponseEntity<>(domiciliarioUpdated,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}	
	
	@DeleteMapping("/domiciliarios/{objectId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> delete(@PathVariable("objectId") Long id) {
		try {
			dRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
}
