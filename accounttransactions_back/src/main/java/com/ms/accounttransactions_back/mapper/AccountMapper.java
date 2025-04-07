package com.ms.accounttransactions_back.mapper;
import com.ms.accounttransactions_back.dto.AccountByAccountNumberDto;
import com.ms.accounttransactions_back.dto.AccountDto;
import com.ms.accounttransactions_back.model.entity.Account;
import org.mapstruct.Mapper;

/**
 * @author : Freddy Torres
 * file :  AccountMapper
 * @since : 4/4/2025, vie
 **/

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDto toDto(Account account);
    AccountByAccountNumberDto toAccountNumberDto(Account account);
    Account toEntity(AccountDto accountDto);
}
