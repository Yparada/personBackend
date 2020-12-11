package com.example.security.service;

import com.example.security.entity.Rol;
import com.example.security.enums.RolName;

public interface RolService {
    public Rol getByRolName(RolName rolName);
    public Rol save(Rol rol);

}
