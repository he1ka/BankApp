package com.telran.bankapp.service;

import com.telran.bankapp.entity.Account;
import com.telran.bankapp.exception.AccountNotFoundException;
import com.telran.bankapp.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account updateAccountById(Long id, Account updatedAccountData)
    {
        Account existingAccount = getAccount(id);

        if (updatedAccountData.getEmail() != null) {
            existingAccount.setEmail(updatedAccountData.getEmail());
        }

        if (updatedAccountData.getFirstName() != null) {
            existingAccount.setFirstName(updatedAccountData.getFirstName());
        }

        if (updatedAccountData.getLastName() != null) {
            existingAccount.setLastName(updatedAccountData.getLastName());
        }

        if (updatedAccountData.getCountry() != null) {
            existingAccount.setCountry(updatedAccountData.getCountry());
        }

        if (updatedAccountData.getCity() != null) {
            existingAccount.setCity(updatedAccountData.getCity());
        }

        return saveAccount(existingAccount);
    }

    public Account getAccount(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("id = " + id));
    }
}
