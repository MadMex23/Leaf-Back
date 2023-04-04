package com.petersen.academit.leaf2.services;

import com.petersen.academit.leaf2.DTO.AccountDTO;
import com.petersen.academit.leaf2.entity.Account;
import com.petersen.academit.leaf2.entity.Transaction;
import com.petersen.academit.leaf2.entity.User;
import com.petersen.academit.leaf2.mapper.AccountMapper;
import com.petersen.academit.leaf2.repository.AccountRepository;
import com.petersen.academit.leaf2.repository.TransactionRepository;
import com.petersen.academit.leaf2.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AccountServiceImp implements AccountService {

    private final UserRepository uRepo;
    private final AccountRepository aRepo;
    private final AccountMapper aMapper;
    private final TransactionRepository tRepo;
    private final TransactionServiceImp tService;

    public AccountServiceImp(AccountRepository aRepo, AccountMapper aMapper, TransactionRepository tRepo,
                             UserRepository uRepo, TransactionServiceImp tService) {
        this.aRepo = aRepo;
        this.tRepo = tRepo;
        this.aMapper = aMapper;
        this.uRepo = uRepo;
        this.tService = tService;
    }

    @Override
    public AccountDTO getAccountById(Integer id) {
        Optional<Account> accountExists = this.aRepo.findById(id);
        if(accountExists.isPresent()) {
            AccountDTO accountDTO = this.aMapper.entityToDTO(accountExists.get());
            accountDTO.setUserId(accountExists.get().getUser().getId());
            accountDTO.setTransactions(this.tService.getTransactionsByAccount(id));
            return accountDTO;
        }
        return null;
    }

    @Override
    public ResponseEntity<?> getAccountsByUser(Integer userId) {
        Map<String, Object> response = new HashMap<>();
        Optional<User> userExists = this.uRepo.findById(userId);
        if (userExists.isPresent()){
            List<Account> accounts = this.aRepo.findAllByUserId(userId);
            Stream<AccountDTO> stream = accounts.stream().map(account -> {
                AccountDTO accountDTO = this.aMapper.entityToDTO(account);
                accountDTO.setUserId(userId);
                return accountDTO;
            });
            ArrayList<AccountDTO> accountsDTO = stream.collect(Collectors.toCollection(ArrayList::new));
            response.put("success", true);
            response.put("data", accountsDTO);
            return ResponseEntity.ok(response);
        };
        response.put("success", false);
        response.put("message", "User doesn't exists.");
        return ResponseEntity.badRequest().body(response);
    }

    @Override
    public Map<String, Object> addAccount(Account account) {
        Map<String, Object> response = new HashMap<>();
        Account accountExists = this.aRepo.findAllByUserId(account.getUser().getId()).stream().filter(acc -> acc.getName().trim().equalsIgnoreCase(account.getName().trim())).findAny().orElse(null);
        if (accountExists == null) {
            this.aRepo.save(account);
            response.put("success", true);
            response.put("message", "Account added successfully.");
        } else {
            response.put("success", false);
            response.put("message", "The account already exists.");
        }
        return response;
    }

    @Override
    public ResponseEntity<?> updateAccount(Integer accountId, Account newInfo) {
        Map<String, Object> response = new HashMap<>();
        Optional<Account> accountExists = this.aRepo.findById(accountId);
        if (accountExists.isPresent()) {
            Account accountToUpdate = accountExists.get();
            accountToUpdate.setName(newInfo.getName());
            accountToUpdate.setType(newInfo.getType());
            accountToUpdate.setInitialBalance(newInfo.getInitialBalance());
            this.aRepo.save(accountToUpdate);
            response.put("success", true);
            response.put("message", "Account updated.");
            return ResponseEntity.ok(response);
        }
        response.put("success", false);
        response.put("message", "Account doesn't exists.");
        return ResponseEntity.badRequest().body(response);
    }

    @Override
    public void deleteAccount(Integer accountId) {
        ArrayList<Transaction> transactions = this.tRepo.findAllByAccountId(accountId);
        if (transactions.stream().findFirst().isPresent()) {
            transactions.forEach(transaction -> {
                this.tRepo.deleteById(transaction.getId());
            });
        }
        this.aRepo.deleteById(accountId);
    }

}
