package com.example.security.controller;

import com.example.dto.Mensaje;
import com.example.security.dto.JwtDto;
import com.example.security.dto.LoginUser;
import com.example.security.dto.NewUser;
import com.example.security.entity.Rol;
import com.example.security.entity.User;
import com.example.security.enums.RolName;
import com.example.security.jwt.JwtProvider;
import com.example.security.service.RolService;
import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private RolService rolService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/new")
    public ResponseEntity<?> newUser(@Valid @RequestBody NewUser newUser, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity(new Mensaje("Campos invalidos"), HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByUserName(newUser.getUserName())){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByEmail(newUser.getEmail())){
            return new ResponseEntity(new Mensaje("Ese email ya existe"), HttpStatus.BAD_REQUEST);
        }
        User user = new User(newUser.getName(), newUser.getUserName(), newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()));
        Set<Rol> rols = new HashSet<>();
        rols.add(rolService.getByRolName(RolName.ROLE_USER));
        if (newUser.getRols().contains("admin")){
            rols.add(rolService.getByRolName(RolName.ROLE_ADMIN));
        }
        user.setRols(rols);
        userService.save(user);
        return new ResponseEntity(new Mensaje("usuario creado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity(new Mensaje("Campos invalidos"), HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
