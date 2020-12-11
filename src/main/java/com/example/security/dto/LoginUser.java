package com.example.security.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class LoginUser {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}
