package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long>{

}
