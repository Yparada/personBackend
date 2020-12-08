package com.example.service;

import java.util.List;

import com.example.model.Pais;

public interface PaisService {
	public List<Pais> listAllPais();
	public Pais getPais(Long id);

}
