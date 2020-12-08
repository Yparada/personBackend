package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "persona")
@Data @AllArgsConstructor @NoArgsConstructor
public class Persona {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String apellido;
	private int edad;
	
	@ManyToOne
	@JoinColumn(name = "id_pais")
	private Pais pais;
	
	@ManyToOne
	@JoinColumn(name = "id_estado")
	private Estado estado;
	
}
