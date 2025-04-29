package com.ms.accounttransactions_back.adapter.out.persistence;

import com.ms.accounttransactions_back.adapter.out.persistence.entity.TransactionEntity;
import com.ms.accounttransactions_back.adapter.out.persistence.mapper.AccountMapper;
import com.ms.accounttransactions_back.adapter.out.persistence.repository.AccountRepository;
import com.ms.accounttransactions_back.adapter.out.persistence.repository.ClientRepository;
import com.ms.accounttransactions_back.adapter.out.persistence.repository.TransactionRepository;
import com.ms.accounttransactions_back.application.exception.NotFoundException;
import com.ms.accounttransactions_back.application.port.out.LoadAccountPort;
import com.ms.accounttransactions_back.common.component.PersistenceAdapter;
import com.ms.accounttransactions_back.domain.Account;
import com.ms.accounttransactions_back.domain.enums.TransactionType;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : Freddy Torres
 * file : AccountPersistenceAdapter
 * @since : 25/4/2025, vie
 **/

@PersistenceAdapter
@AllArgsConstructor
public class AccountPersistenceAdapter implements LoadAccountPort {
    private static final String NOT_FOUND_MESSAGE = "Informacion no encontrada";
    private static final String APERTURA_CUENTA_MESSAGE = "APERTURA CUENTA";
    private static final String ERROR_EJECUTAR_MESSAGE = "No se puedo cambiar de estado, la cuenta tiene dinero";
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final TransactionRepository transactionRepository;
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    @Override
    @Transactional
    public Account saveAccount(Account account) {
        var existingAccount = clientRepository.findAllByIdentificationAndStatusTrue(account.getClient().getIdentification())
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        account.setClient(existingAccount);
        account.setAccountNumber(accountNumber());
        account.setStatus(true);
        var savedAccount = accountRepository.save(accountRepository.save(AccountMapper.domainToEntity(account)));
        if (savedAccount.getAccountId() != null) {
            var transaction = new TransactionEntity();
            transaction.setDescription(APERTURA_CUENTA_MESSAGE);
            transaction.setDate(LocalDateTime.now());
            transaction.setTransactionType(TransactionType.DEPOSITO);
            transaction.setAmount(account.getInitialBalance());
            transaction.setBalance(account.getInitialBalance());
            transaction.setAccount(savedAccount);
            transactionRepository.save(transaction);
        }
        return AccountMapper.entityToDomain(savedAccount);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> findByClientIdentification(String identification) {
        var account = accountRepository.findByClientIdentification(identification);
        if (account.isEmpty()) throw new NotFoundException(NOT_FOUND_MESSAGE);
        return account.stream()
                .map(AccountMapper::entityToDomain)
                .toList();
    }


    @Override
    @Transactional
    public Account updateAccount(Long accountNumber) {
        var existingAccount = accountRepository.findTopByAccountNumberAndStatusTrueOrderByTransactionEntitiesDateDesc(accountNumber)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        var transaction = existingAccount.getTransactionEntities().stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        if (transaction.getBalance() != 0) {
            throw new NotFoundException(ERROR_EJECUTAR_MESSAGE);
        }
        existingAccount.setStatus(false);
        return AccountMapper.entityToDomain(accountRepository.save(existingAccount));
    }

    public Long accountNumber() {
        return 100000L + SECURE_RANDOM.nextInt(900000);
    }
}
