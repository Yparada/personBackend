package com.example.dto;

import com.example.entity.Estado;
import com.example.entity.Pais;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PersonaDto {

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    @Min(1)
    @NotNull
    private int edad;

    @NotNull
    private Pais pais;

    @NotBlank
    private Estado estado;
}
