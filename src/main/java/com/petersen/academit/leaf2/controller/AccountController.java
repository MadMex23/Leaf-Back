package com.petersen.academit.leaf2.controller;

import com.petersen.academit.leaf2.DTO.AccountDTO;
import com.petersen.academit.leaf2.entity.Account;
import com.petersen.academit.leaf2.entity.User;
import com.petersen.academit.leaf2.mapper.AccountMapper;
import com.petersen.academit.leaf2.services.AccountServiceImp;
import com.petersen.academit.leaf2.services.UserServiceImp;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "Account Controller")
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(path = "/accounts")
public class AccountController {

    private final AccountServiceImp aService;
    private final AccountMapper aMapper;
    private final UserServiceImp uService;

    public AccountController(AccountServiceImp aService, AccountMapper aMapper, UserServiceImp uService) {
        this.aService = aService;
        this.aMapper = aMapper;
        this.uService = uService;
    }

    @Operation(summary = "Get a user's account by ID")
    @GetMapping(path = "/{accountId}")
    public ResponseEntity<?> get(@PathVariable Integer accountId) {
        Map<String, Object> response = new HashMap<>();
        AccountDTO account = this.aService.getAccountById(accountId);
        if (account != null) {
            response.put("success", true);
            response.put("data", account);
            return ResponseEntity.ok(response);
        }
        response.put("success", false);
        response.put("message", "Account doesn't exists");
        return ResponseEntity.badRequest().body(response);
    }

    @Operation(summary = "Filter accounts by user")
    @GetMapping(path = "/user")
    public ResponseEntity<?> getAccounts(@RequestParam Integer userId) {
        return this.aService.getAccountsByUser(userId);
    }

    @Operation(summary = "Add a new account")
    @PostMapping(path = "/add")
    public ResponseEntity<?> add(@RequestBody AccountDTO body) {
        User user = this.uService.getUserById(body.getUserId());
        Account account = this.aMapper.dtoToEntity(body);
        account.setUser(user);
        Map<String, Object> response = this.aService.addAccount(account);
        if(response.get("success").equals(true)) {
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @Operation(summary = "Edit an existing account")
    @PutMapping("/{accountId}")
    public ResponseEntity<?> update(@PathVariable Integer accountId, @RequestBody AccountDTO body) {
        Account newInfo = this.aMapper.dtoToEntity(body);
        return this.aService.updateAccount(accountId, newInfo);
    }

    @Operation(summary = "Delete an account")
    @DeleteMapping("/{accountId}")
    public ResponseEntity<?> delete(@PathVariable Integer accountId) {
        Map<String, Object> response = new HashMap<>();
        AccountDTO accountExists = this.aService.getAccountById(accountId);
        if (accountExists != null) {
            this.aService.deleteAccount(accountId);
            response.put("success", true);
            response.put("message", "Account deleted.");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "The account doesn't exists.");
            return ResponseEntity.badRequest().body(response);
        }

    }

}
