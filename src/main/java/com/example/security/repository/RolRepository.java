package com.example.security.repository;

import com.example.security.entity.Rol;
import com.example.security.enums.RolName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol,Long> {
    Rol findByRolName(RolName rolName);
}
