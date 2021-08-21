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

import com.Backendpw.model.Domicilio;
//import com.Backendpw.repositorio.DomiciliarioRepository;
import com.Backendpw.repositorio.DomicilioRepository;
//import com.Backendpw.repositorio.SolicitanteRepository;

@CrossOrigin(origins= {"http://localhost:8080", "http://localhost:8081"})
@RestController
@RequestMapping("/api/domicilio")
public class DomicilioController {
	@Autowired
	private DomicilioRepository dRepository;
	/*@Autowired
	private DomiciliarioRepository domiRepository;
	@Autowired
	private SolicitanteRepository solRepositiry;*/
	@GetMapping("/domicilios")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")

	public ResponseEntity<List<Domicilio>> getAlldomicilios(){
		List<Domicilio> domicilios=dRepository.findAll();
		if(domicilios.isEmpty())return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(domicilios,HttpStatus.OK);
	}
	
	@GetMapping("/domicilios/{idDomi}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Domicilio> getdomicilioById(@PathVariable("idDomi") Long idDomi){
		
		Domicilio d = dRepository.findById(idDomi).get();
		if(d != null) {
			return new ResponseEntity<>(d,HttpStatus.OK);	
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	/*@GetMapping("/domicilios/{estado}")
	public ResponseEntity<List<Domicilio>> getdomicilioByestado(@PathVariable("estado") String estado){
		List<Domicilio> d = dRepository.findByEstado(estado);
		if(d != null) {
			return new ResponseEntity<>(d,HttpStatus.OK);	
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}*/
	@PostMapping("/domicilios")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Domicilio> createdomicilio(@RequestBody Domicilio domicilio){
		try {
			Domicilio d = dRepository.save(domicilio);
			return new ResponseEntity<>(d,HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/domicilio/{idDomi}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Domicilio> updatedomicilio(@PathVariable("idDomi") Long idDomi, @RequestBody Domicilio datosDomicilio){
		Domicilio d= dRepository.findById(idDomi).get();
		if(d != null) {
			
			//d.setIdDomicilio(idDomi);
			d.setEstado(datosDomicilio.getEstado());
			d.setTelefonoDes(datosDomicilio.getTelefonoDes());
			d.setNombreDes(datosDomicilio.getNombreDes());
			d.setDirDes(datosDomicilio.getDirDes());
			d.setCoorDes(datosDomicilio.getCoorDes());
			d.setHoraDes(datosDomicilio.getHoraDes());
			d.setHoraSalida(datosDomicilio.getHoraSalida());
			d.setDirSalida(datosDomicilio.getDirSalida());
			d.setCoorSalida(datosDomicilio.getCoorSalida());
			d.setObservacion(datosDomicilio.getObservacion());
			d.setSolicitante(datosDomicilio.getSolicitante());
			d.setDomiciliario(datosDomicilio.getDomiciliario());
			Domicilio domicilioUpdated = dRepository.save(d);
			return new ResponseEntity<>(domicilioUpdated,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}	
	@PutMapping("/estado/{idDomi}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Domicilio> estadoDomicilio(@PathVariable("idDomi") Long idDomi, @RequestBody Domicilio datosDomicilio){
		Domicilio d= dRepository.findById(idDomi).get();
		if(d != null) {
			
			//d.setIdDomicilio(idDomi);
			d.setEstado(datosDomicilio.getEstado());
			
			Domicilio domicilioUpdated = dRepository.save(d);
			return new ResponseEntity<>(domicilioUpdated,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/domicilio/{idDomi}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> delete(@PathVariable("idDomi") Long id) {
		try {
			dRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
