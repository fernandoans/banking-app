package com.example.banking.service.impl;

import com.example.banking.dto.AccountDto;
import com.example.banking.entity.Account;
import com.example.banking.mapper.AccountMapper;
import com.example.banking.repository.AccountRepository;
import com.example.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        return AccountMapper.mapToAccountDto(findAccount(id));
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = findAccount(id);
        account.setBalance(account.getBalance() + amount);
        return AccountMapper.mapToAccountDto(accountRepository.save(account));
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = findAccount(id);

        double newBalance = account.getBalance() - amount;
        if (newBalance < 0) {
            throw new RuntimeException("Não existe dinheiro suficiente na conta para realizar o saque");
        }

        account.setBalance(newBalance);
        return AccountMapper.mapToAccountDto(accountRepository.save(account));
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
            .map(AccountMapper::mapToAccountDto)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = findAccount(id);
        accountRepository.deleteById(id);
    }

    private Account findAccount(Long id) {
        return accountRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Conta não existe no banco de dados."));
    }
}
