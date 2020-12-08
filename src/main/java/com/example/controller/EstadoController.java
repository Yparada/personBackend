package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Estado;
import com.example.model.Pais;
import com.example.service.EstadoService;
import com.example.service.PaisService;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private PaisService paisService;
	
	@GetMapping("{id}")
	private ResponseEntity<List<Estado>> getAllEstadosByPais(@PathVariable("id")Long idPais){
		Pais pais = paisService.getPais(idPais);
		
		return ResponseEntity.ok(estadoService.listEstadoById(pais));
		
	}
	
}
