package com.petersen.academit.leaf2.controller;

import com.petersen.academit.leaf2.DTO.AccountDTO;
import com.petersen.academit.leaf2.DTO.TransactionDTO;
import com.petersen.academit.leaf2.entity.Account;
import com.petersen.academit.leaf2.entity.Transaction;
import com.petersen.academit.leaf2.mapper.AccountMapper;
import com.petersen.academit.leaf2.mapper.TransactionMapper;
import com.petersen.academit.leaf2.services.AccountServiceImp;
import com.petersen.academit.leaf2.services.TransactionServiceImp;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(tags = "Transaction Controller")
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(path = "/transactions")
public class TransactionController {

    private final TransactionServiceImp tService;
    private final TransactionMapper tMapper;
    private final AccountServiceImp aService;
    private final AccountMapper aMapper;

    public TransactionController(TransactionServiceImp tService, TransactionMapper tMapper,
                                 AccountServiceImp aService, AccountMapper aMapper) {
        this.tService = tService;
        this.tMapper = tMapper;
        this.aService = aService;
        this.aMapper = aMapper;
    }

    @Operation(summary = "Get all transactions by account")
    @GetMapping(path = "/account")
    public Map<String, Object> get(@RequestParam Integer accountId) {
        Map<String, Object> response = new HashMap<>();
        ArrayList<TransactionDTO> transactions =
                this.tService.getTransactionsByAccount(accountId);
        transactions.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
        if (transactions.stream().findFirst().isPresent()) {
            response.put("success", true);
            response.put("data", transactions);
        } else {
            response.put("success", false);
            response.put("message", "No transactions found.");
        }
        return response;
    }

    @Operation(summary = "Get all transactions by account and type")
    @GetMapping(path = "/filter")
    public Map<String, Object> get(@RequestParam Integer accountId, String type) {
        Map<String, Object> response = new HashMap<>();
        ArrayList<TransactionDTO> transactions =
                this.tService.getTransactionsByAccountAndType(accountId, type);
        transactions.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
        if (transactions.stream().findFirst().isPresent()) {
            response.put("success", true);
            response.put("data", transactions);
        } else {
            response.put("success", false);
            response.put("message", "No transactions found.");
        }
        return response;
    }

    @Operation(summary = "Register a new transaction")
    @PostMapping(path = "/add")
    public ResponseEntity<?> add(@RequestBody TransactionDTO transaction) {
        AccountDTO account = this.aService.getAccountById(transaction.getAccountId());
        Transaction trans = this.tMapper.dtoToEntity(transaction);
        trans.setAccount(this.aMapper.dtoToEntity(account));
        Map<String, Object> response = this.tService.addTransaction(trans);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete a transaction")
    @DeleteMapping(path = "/{transactionId}")
    public ResponseEntity<?> delete(@PathVariable Integer transactionId) {
        Map<String, Object> response = this.tService.deleteTransaction(transactionId);
        return ResponseEntity.ok(response);
    }
}
