package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Estado;
import com.example.model.Pais;

public interface EstadoRepository extends JpaRepository<Estado, Long>{
	public List<Estado> findByPais(Pais pais);

}
