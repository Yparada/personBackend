package com.example.service;

import java.util.List;

import com.example.model.Estado;
import com.example.model.Pais;

public interface EstadoService {
	public List<Estado> listEstadoById(Pais pais);
	public List<Estado> getAllEstados();

}
