package com.example.blogger.service;

import com.example.blogger.model.User;

public interface UserService {
    User findUserByEmail(String email);
}
