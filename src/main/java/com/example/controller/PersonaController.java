package com.example.controller;

import java.util.List;

import com.example.dto.Mensaje;
import com.example.dto.PersonaDto;
import org.apache.commons.lang3.StringUtils;
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

import com.example.entity.Persona;
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
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(persona);
	}

	@PostMapping
	private ResponseEntity<Persona> savePersona(@RequestBody PersonaDto personaDto){
		if (StringUtils.isAllBlank(personaDto.getNombre())){
			return new ResponseEntity(new Mensaje("El campo nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isAllBlank(personaDto.getApellido())){
			return new ResponseEntity(new Mensaje("El campo apellido es obligatorio"), HttpStatus.BAD_REQUEST);
		}
		if (personaDto.getEdad()<1){
			return new ResponseEntity(new Mensaje("El campo edad debe ser mayor que cero"), HttpStatus.BAD_REQUEST);
		}
		if (null == personaDto.getPais()){
			return new ResponseEntity(new Mensaje("El campo pais es obligatorio"), HttpStatus.BAD_REQUEST);
		}
		if(null == personaDto.getEstado()){
			return new ResponseEntity(new Mensaje("El campo estado es obligatorio"), HttpStatus.BAD_REQUEST);
		}

		Persona personaNueva = Persona.builder()
				.nombre(personaDto.getNombre())
				.apellido(personaDto.getApellido())
				.edad(personaDto.getEdad())
				.pais(personaDto.getPais())
				.estado(personaDto.getEstado()).build();
		personaServicee.createProduct(personaNueva);
		return new ResponseEntity(new Mensaje("Persona agregada"), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "{id}")
	private ResponseEntity<Persona> deletePersona(@PathVariable("id") Long id){
		Persona personaDel = personaServicee.getProduct(id);
		if(null==personaDel) {
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.BAD_REQUEST);
		}
		personaServicee.deleteProduct(id);
		return new ResponseEntity(new Mensaje("Persona eliminada"), HttpStatus.OK);
	}

	@PutMapping("{id}")
	private ResponseEntity<Persona> updatePersona(@PathVariable("id") Long id,@RequestBody PersonaDto personaDto){
		Persona persona = personaServicee.getProduct(id);
		if(null==persona) {
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		}
		if (StringUtils.isAllBlank(personaDto.getNombre())){
			return new ResponseEntity(new Mensaje("El campo nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isAllBlank(personaDto.getApellido())){
			return new ResponseEntity(new Mensaje("El campo apellido es obligatorio"), HttpStatus.BAD_REQUEST);
		}
		if (personaDto.getEdad()<0){
			return new ResponseEntity(new Mensaje("El campo edad debe ser mayor que cero"), HttpStatus.BAD_REQUEST);
		}
		if (null == personaDto.getPais()){
			return new ResponseEntity(new Mensaje("El campo pais es obligatorio"), HttpStatus.BAD_REQUEST);
		}
		if(null == personaDto.getEstado()){
			return new ResponseEntity(new Mensaje("El campo estado es obligatorio"), HttpStatus.BAD_REQUEST);
		}

		persona.setNombre(personaDto.getNombre());
		persona.setApellido(personaDto.getApellido());
		persona.setEdad(personaDto.getEdad());
		persona.setPais(personaDto.getPais());
		persona.setEstado(personaDto.getEstado());
		personaServicee.createProduct(persona);
		return new ResponseEntity(new Mensaje("Persona actualizado"), HttpStatus.OK);
	}

}
