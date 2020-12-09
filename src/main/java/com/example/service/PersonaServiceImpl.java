package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Persona;
import com.example.repository.PersonaRepository;


@Service
public class PersonaServiceImpl implements PersonaService{
	
	@Autowired
	private PersonaRepository personaRepository;

	@Override
	public List<Persona> listAllPersona() {
		return personaRepository.findAll();
	}

	@Override
	public Persona getProduct(Long id) {
		return personaRepository.findById(id).orElse(null);
	}

	@Override
	public Persona createProduct(Persona persona) {
		return personaRepository.save(persona);
	}

	@Override
	public Persona updateProduct(Persona persona) {
		Persona personadb = getProduct(persona.getId());
		if(null == personadb) {
			return null;
		}
		
		personadb.setNombre(persona.getNombre());
		personadb.setApellido(persona.getApellido());
		personadb.setEdad(persona.getEdad());
		personadb.setPais(persona.getPais());
		personadb.setEstado(persona.getEstado());
		
		return personaRepository.save(personadb);
	}

	@Override
	public Persona deleteProduct(Long id) {
		Persona personadb = getProduct(id);
		if(null == personadb) {
			return null;
		}
		personaRepository.delete(personadb);
		
		return personadb;
	}

}
