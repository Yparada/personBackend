package com.example.security.service;

import com.example.security.entity.User;

public interface UserService {

    public User getByUserName(String userName);
    public boolean existsByUserName(String userName);
    public boolean existsByEmail(String email);
    public User save(User user);
}
