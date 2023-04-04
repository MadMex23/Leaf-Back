package com.petersen.academit.leaf2.mapper;

import com.petersen.academit.leaf2.DTO.TransactionDTO;
import com.petersen.academit.leaf2.entity.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionDTO entityToDTO(Transaction transaction);
    Transaction dtoToEntity(TransactionDTO transaction);
}
