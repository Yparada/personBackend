package com.example.security.service;

import com.example.security.entity.Rol;
import com.example.security.enums.RolName;
import com.example.security.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RolServiceImpl implements RolService{

    @Autowired
    private RolRepository rolRepository;

    @Override
    public Rol getByRolName(RolName rolName) {
        return rolRepository.findByRolName(rolName);
    }

    @Override
    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }
}
