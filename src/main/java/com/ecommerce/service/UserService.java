package com.ecommerce.service;

import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String userRegister(String email){

        return "logged in successfullly";
    }
    public String userLogin(String email){

        return "logged in successfullly";
    }
}
