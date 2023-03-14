package com.petersen.academit.leaf2.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private Integer id;
    private String type;
    private String description;
    private LocalDate date;
    private Integer amount;
    private Integer accountId;
}
