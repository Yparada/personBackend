package com.example.service;

import java.util.List;

import com.example.model.Persona;

public interface PersonaService {
	
	public List<Persona> listAllPersona();
    public Persona getProduct(Long id);
    public Persona createProduct(Persona persona);
    public Persona updateProduct(Persona persona);
    public Persona deleteProduct(Long id);

}
