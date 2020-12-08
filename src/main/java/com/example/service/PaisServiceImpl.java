package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Pais;
import com.example.repository.PaisRepository;

@Service
public class PaisServiceImpl implements PaisService{
	
	@Autowired
	private PaisRepository paisRepository;

	@Override
	public List<Pais> listAllPais() {
		return paisRepository.findAll();
	}

	@Override
	public Pais getPais(Long id) {
		return paisRepository.findById(id).orElse(null);
	}

}
