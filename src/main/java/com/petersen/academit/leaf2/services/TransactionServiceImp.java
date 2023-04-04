package com.petersen.academit.leaf2.services;

import com.petersen.academit.leaf2.DTO.AccountDTO;
import com.petersen.academit.leaf2.DTO.TransactionDTO;
import com.petersen.academit.leaf2.entity.Transaction;
import com.petersen.academit.leaf2.mapper.TransactionMapper;
import com.petersen.academit.leaf2.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TransactionServiceImp implements TransactionService{

    private final TransactionRepository tRepo;
    private final TransactionMapper tMapper;

    public TransactionServiceImp(TransactionRepository tRepo, TransactionMapper tMapper) {
        this.tRepo = tRepo;
        this.tMapper = tMapper;
    }

    @Override
    public ArrayList<TransactionDTO> getTransactionsByAccount(Integer accountId) {
        Stream<TransactionDTO> stream = tRepo.findAllByAccountId(accountId).stream().map(transaction -> {
            TransactionDTO trans = this.tMapper.entityToDTO(transaction);
            trans.setAccountId(accountId);
            return trans;
        });
        return stream.collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ArrayList<TransactionDTO> getTransactionsByAccountAndType(Integer accountId, String type) {
        Stream<TransactionDTO> stream = tRepo.findAllByAccountIdAndType(accountId, type).stream().map(transaction -> {
            TransactionDTO trans = this.tMapper.entityToDTO(transaction);
            trans.setAccountId(accountId);
            return trans;
        });
        return stream.collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Map<String, Object> addTransaction(Transaction transaction) {
        Map<String, Object> response = new HashMap<>();
        this.tRepo.save(transaction);
        response.put("success", true);
        response.put("message", "Transaction registered.");
        return response;
    }

    @Override
    public Map<String, Object> deleteTransaction(Integer transactionId) {
        Map<String, Object> response = new HashMap<>();
        Optional<Transaction> transactionExists = this.tRepo.findById(transactionId);
        if(transactionExists.isPresent()) {
            this.tRepo.deleteById(transactionId);
            response.put("success", true);
            response.put("message", "Transaction deleted.");
            return response;
        }
        response.put("success", false);
        response.put("message", "Transaction doesn't exist.");
        return response;
    }
}
