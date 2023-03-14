package com.petersen.academit.leaf2.services;

import com.petersen.academit.leaf2.entity.User;

import java.util.Optional;

public interface UserService {
    public boolean loginValidate(User user);
}
