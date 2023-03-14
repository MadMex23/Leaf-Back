package com.petersen.academit.leaf2.services;

import com.petersen.academit.leaf2.entity.User;
import com.petersen.academit.leaf2.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository uRepo;

    public UserServiceImp(UserRepository uRepo) {
        this.uRepo = uRepo;
    }

    @Override
    public boolean loginValidate(User user) {
        Optional<User> userExists = uRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
        return userExists.isPresent();
    }
}
