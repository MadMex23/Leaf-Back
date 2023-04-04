package com.petersen.academit.leaf2.services;

import com.petersen.academit.leaf2.DTO.TransactionDTO;
import com.petersen.academit.leaf2.entity.Transaction;

import java.util.ArrayList;
import java.util.Map;

public interface TransactionService {

 ArrayList<TransactionDTO> getTransactionsByAccount(Integer accountId);

 ArrayList<TransactionDTO> getTransactionsByAccountAndType(Integer accountId, String type);
 Map<String, Object> addTransaction(Transaction transaction);

 Map<String, Object> deleteTransaction(Integer transactionId);
}
