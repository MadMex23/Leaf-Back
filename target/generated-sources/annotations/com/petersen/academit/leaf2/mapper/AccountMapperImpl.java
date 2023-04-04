package com.petersen.academit.leaf2.mapper;

import com.petersen.academit.leaf2.DTO.AccountDTO;
import com.petersen.academit.leaf2.entity.Account;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-26T23:00:02-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Amazon.com Inc.)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public AccountDTO entityToDTO(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountDTO accountDTO = new AccountDTO();

        accountDTO.setId( account.getId() );
        accountDTO.setName( account.getName() );
        accountDTO.setType( account.getType() );
        accountDTO.setInitialBalance( account.getInitialBalance() );

        return accountDTO;
    }

    @Override
    public Account dtoToEntity(AccountDTO account) {
        if ( account == null ) {
            return null;
        }

        Account account1 = new Account();

        account1.setId( account.getId() );
        account1.setName( account.getName() );
        account1.setType( account.getType() );
        account1.setInitialBalance( account.getInitialBalance() );

        return account1;
    }
}
