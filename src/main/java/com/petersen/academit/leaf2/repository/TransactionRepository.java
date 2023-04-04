package com.petersen.academit.leaf2.repository;

import com.petersen.academit.leaf2.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    ArrayList<Transaction> findAllByAccountId(Integer accountId);

    @Query("select t from Transaction as t where t.account.id = ?1 and t.type = ?2")
    ArrayList<Transaction> findAllByAccountIdAndType(Integer accountId, String type);
}
