package com.petersen.academit.leaf2.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Integer id;
    private String name;
    private String type;
    private Integer initialBalance;
    private Integer userId;
    private ArrayList<TransactionDTO> transactions;
}
