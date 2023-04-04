package com.petersen.academit.leaf2.mapper;

import com.petersen.academit.leaf2.DTO.AccountDTO;
import com.petersen.academit.leaf2.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDTO entityToDTO(Account account);
    Account dtoToEntity(AccountDTO account);
}
