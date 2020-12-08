package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Estado;
import com.example.model.Pais;
import com.example.repository.EstadoRepository;

@Service
public class EstadoServiceImpl implements EstadoService{
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Override
	public List<Estado> listEstadoById(Pais pais) {
		return estadoRepository.findByPais(pais);
	}

	@Override
	public List<Estado> getAllEstados() {
		return estadoRepository.findAll();
	}

}
