package com.petersen.academit.leaf2.services;

import com.petersen.academit.leaf2.DTO.AccountDTO;
import com.petersen.academit.leaf2.entity.Account;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import java.util.Map;


public interface AccountService {
   AccountDTO getAccountById(Integer id);
   ResponseEntity<?> getAccountsByUser(Integer userId);
   Map<String, Object> addAccount(Account account);
   ResponseEntity<?> updateAccount(Integer accountId, Account newInfo);
   void deleteAccount(Integer accountId);
}
