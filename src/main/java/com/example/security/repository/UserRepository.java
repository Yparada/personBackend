package com.example.security.repository;

import com.example.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByName(String name);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
}
