package com.example.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Persona;
import com.example.service.PersonaService;
import com.example.service.PersonaService;

@RestController
@RequestMapping("/personas")
public class PersonaController {

	@Autowired
	private PersonaService personaServicee;

	@GetMapping
	private ResponseEntity<List<Persona>> getAllPersona(){
		return ResponseEntity.ok(personaServicee.listAllPersona());
	}
	
	@GetMapping("{id}")
	private ResponseEntity<Persona> getPersona(@PathVariable("id") Long id){
		Persona persona = personaServicee.getProduct(id);
		if(null==persona) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(persona);
	}
	
	@PostMapping
	private ResponseEntity<Persona> savePersona(@RequestBody Persona persona){
		Persona personaNueva = personaServicee.createProduct(persona);
		return ResponseEntity.status(HttpStatus.CREATED).body(personaNueva);
	}
	
	@DeleteMapping(value = "{id}")
	private ResponseEntity<Persona> deletePersona(@PathVariable("id") Long id){
		Persona personaDel = personaServicee.deleteProduct(id);
		if(null==personaDel) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(personaDel);
	}
	
	@PutMapping("{id}")
	private ResponseEntity<Persona> updatePersona(@PathVariable("id") Long id,@RequestBody Persona persona){
		persona.setId(id);
		Persona personadb = personaServicee.updateProduct(persona);
		if(null==persona) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(personadb);
		
	}

}
