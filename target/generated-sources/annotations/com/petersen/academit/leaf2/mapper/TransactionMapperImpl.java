package com.petersen.academit.leaf2.mapper;

import com.petersen.academit.leaf2.DTO.TransactionDTO;
import com.petersen.academit.leaf2.entity.Transaction;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-26T23:00:03-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Amazon.com Inc.)"
)
@Component
public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public TransactionDTO entityToDTO(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }

        TransactionDTO transactionDTO = new TransactionDTO();

        transactionDTO.setId( transaction.getId() );
        transactionDTO.setType( transaction.getType() );
        transactionDTO.setDescription( transaction.getDescription() );
        transactionDTO.setDate( transaction.getDate() );
        transactionDTO.setAmount( transaction.getAmount() );

        return transactionDTO;
    }

    @Override
    public Transaction dtoToEntity(TransactionDTO transaction) {
        if ( transaction == null ) {
            return null;
        }

        Transaction transaction1 = new Transaction();

        transaction1.setId( transaction.getId() );
        transaction1.setType( transaction.getType() );
        transaction1.setDescription( transaction.getDescription() );
        transaction1.setDate( transaction.getDate() );
        transaction1.setAmount( transaction.getAmount() );

        return transaction1;
    }
}
