package com.geekbrains.septembermarket.services;

import com.geekbrains.septembermarket.entities.User;
import com.geekbrains.septembermarket.utils.SystemUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);
    User save(SystemUser systemUser);
}
