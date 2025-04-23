package com.ms.accounttransactions_back.adapter.out.persistence.mapper;
import com.ms.accounttransactions_back.domain.AccountByAccountNumber;
import com.ms.accounttransactions_back.domain.Account;
import org.mapstruct.Mapper;

/**
 * @author : Freddy Torres
 * file :  AccountMapper
 * @since : 4/4/2025, vie
 **/

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toDto(com.ms.accounttransactions_back.adapter.out.persistence.entity.Account account);
    AccountByAccountNumber toAccountNumberDto(com.ms.accounttransactions_back.adapter.out.persistence.entity.Account account);
    com.ms.accounttransactions_back.adapter.out.persistence.entity.Account toEntity(Account account);
}
