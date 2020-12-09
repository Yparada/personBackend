package com.example.service;

import java.util.List;

import com.example.entity.Estado;
import com.example.entity.Pais;

public interface EstadoService {
	public List<Estado> listEstadoById(Pais pais);
	public List<Estado> getAllEstados();

}
