package com.petersen.academit.leaf2.services;

import com.petersen.academit.leaf2.DTO.UserLoginDTO;
import com.petersen.academit.leaf2.entity.User;

import java.util.Optional;

public interface UserService {

    User getUserById(Integer id);

    Optional<User> getUserByEmail(String email);
    User loginValidate(User user);
    void createUser(User user);
    boolean passwordValidate(Integer id, String password);
    void deleteUser(Integer id);

}
