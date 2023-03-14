package com.petersen.academit.leaf2.controller;

import com.petersen.academit.leaf2.entity.User;
import com.petersen.academit.leaf2.repository.UserRepository;
import com.petersen.academit.leaf2.services.UserServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(path = "/users")
public class UserController {


    private final UserServiceImp uService;

    public UserController(UserServiceImp uService) {
        this.uService = uService;
    }

//    @GetMapping("/all")
//    public List<User> find() {
//        return this.uService.findAll();
//    }

//    @PostMapping(path = "/add")
//    public ResponseEntity<?> add(@RequestBody User body) {
//        return ResponseEntity.status(HttpStatus.CREATED).body();
//    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody User body) {
        if(body.getEmail().isEmpty() || body.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing Parameters.");
        } else if (this.uService.loginValidate(body)){
            return ResponseEntity.status(HttpStatus.OK).body("Welcome!");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Wrong Credentials.");
    }
}
