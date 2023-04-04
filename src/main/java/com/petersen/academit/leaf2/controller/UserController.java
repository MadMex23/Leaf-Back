package com.petersen.academit.leaf2.controller;

import com.petersen.academit.leaf2.entity.User;
import com.petersen.academit.leaf2.mapper.UserMapper;
import com.petersen.academit.leaf2.services.UserServiceImp;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@OpenAPIDefinition(info = @Info(title = "Leaf API Documentation"))
@Api(tags = "User Controller")
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(path = "/users")
public class UserController {


    private final UserServiceImp uService;
    private final UserMapper uMapper;

    public UserController(UserServiceImp uService, UserMapper uMapper) {
        this.uService = uService;
        this.uMapper = uMapper;
    }

    @Operation(summary = "Get user by ID")
    @GetMapping(path = "/{userId}")
    public ResponseEntity<?> get(@PathVariable Integer userId) {
        try {
        return ResponseEntity.status(HttpStatus.OK).body(this.uMapper.entityToDTO(this.uService.getUserById(userId)));
        }
        catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @Operation(summary = "Create a new user")
    @PostMapping(path = "/signup")
    public ResponseEntity<?> add(@RequestBody User body) {
        Map<String, Object> response = new HashMap<>();
        if (body.getName().isEmpty() || body.getLastName().isEmpty() || body.getEmail().isEmpty() || body.getPassword().isEmpty()) {
            response.put("success", false);
            response.put("message", "Missing Parameters.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else if (this.uService.getUserByEmail(body.getEmail()).isPresent()) {
            response.put("success", false);
            response.put("message", "User already exists.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else {
            this.uService.createUser(body);
            response.put("success", true);
            response.put("message", "User created successfully.");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @Operation(summary = "Log in with user credentials")
    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody User body) {
            Map<String, Object> response = new HashMap<>();
            try {
                if (body.getEmail().isEmpty() || body.getPassword().isEmpty()) {
                    response.put("success", false);
                    response.put("message", "Missing Parameters.");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                } else {
                    response.put("success", true);
                    response.put("message", "Welcome!");
                    response.put("data", this.uMapper.entityToLoginDTO(this.uService.loginValidate(body)));
                    return ResponseEntity.ok(response);
                }
            } catch (Exception exception) {
                response.put("success", false);
                response.put("message", exception.getMessage());
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }

    }

    @Operation(summary = "Delete a user")
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> delete(@PathVariable Integer userId, @RequestBody User password) {
        Map<String, Object> response = new HashMap<>();
        if (this.uService.passwordValidate(userId, password.getPassword())) {
            this.uService.deleteUser(userId);
            response.put("success", true);
            response.put("message", "User deleted.");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "Wrong Credentials");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

    }
}
