package com.ecommerce.service;

import com.ecommerce.entity.User;

import java.util.List;

public interface UserService {

    User registerUser(User user);
    List<User> getAllUser();
}
