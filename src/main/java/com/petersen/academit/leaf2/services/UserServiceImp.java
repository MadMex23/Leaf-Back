package com.petersen.academit.leaf2.services;

import com.petersen.academit.leaf2.DTO.AccountDTO;
import com.petersen.academit.leaf2.DTO.UserLoginDTO;
import com.petersen.academit.leaf2.entity.Account;
import com.petersen.academit.leaf2.entity.User;
import com.petersen.academit.leaf2.mapper.AccountMapper;
import com.petersen.academit.leaf2.mapper.UserMapper;
import com.petersen.academit.leaf2.repository.AccountRepository;
import com.petersen.academit.leaf2.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository uRepo;
    private final AccountRepository aRepo;
    private final AccountServiceImp aService;

    public UserServiceImp(UserRepository uRepo, AccountRepository aRepo, AccountServiceImp aService) {
        this.uRepo = uRepo;
        this.aRepo = aRepo;
        this.aService = aService;
    }

    @Override
    public User loginValidate(User user) {
        Optional<User> userExists = uRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (userExists.isPresent()) {
            return userExists.get();
        }
        throw new RuntimeException("Wrong Credentials.");
    }

    @Override
    public void createUser(User user) {
        uRepo.save(user);
    }

    @Override
    public boolean passwordValidate(Integer id, String password) {
        Optional<User> user = uRepo.findById(id);
        return user.map(value -> value.getPassword().equals(password)).orElse(false);
    }

    @Override
    public void deleteUser(Integer id) {
        List<Account> accounts = this.aRepo.findAllByUserId(id);
        if(accounts.stream().findFirst().isPresent()) {
            accounts.forEach(account -> {
                this.aService.deleteAccount(account.getId());
            });
        }
        uRepo.deleteById(id);
    }

    @Override
    public User getUserById(Integer id) {
        if (uRepo.findById(id).isPresent()) {
            return uRepo.findById(id).get();
        } else {
        throw new RuntimeException("User with that Id doesn't exist");
        }
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return uRepo.findByEmail(email);
    }

}
