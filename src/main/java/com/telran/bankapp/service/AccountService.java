package com.telran.bankapp.service;

import com.telran.bankapp.entity.Account;
import com.telran.bankapp.exception.AccountNotFoundException;
import com.telran.bankapp.exception.NotEnoughMoneyException;
import com.telran.bankapp.repository.AccountRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final TransactionService transactionService;

    public AccountService(AccountRepository accountRepository, TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
    }

    public List<Account> getAllAccounts(String date, List<String> cities, String sort) {
        Sort sortCondition = Sort.by(Sort.Direction.ASC, "id");

        if (Objects.equals(sort, "creationDate")) {
            sortCondition = Sort.by(Sort.Direction.ASC, "creationDate");
        }

        if (Objects.equals(sort, "-creationDate")) {
            sortCondition = Sort.by(Sort.Direction.DESC, "creationDate");
        }

        if (date != null && (cities == null || cities.isEmpty())) {
            return accountRepository.findByCreationDate(LocalDate.parse(date), sortCondition);
        }

        if (date == null && cities != null && !cities.isEmpty()) {
            return accountRepository.findByCityInIgnoreCase(cities, sortCondition);
        }

        if (date != null && cities != null && !cities.isEmpty()) {
            return accountRepository.findByCreationDateAndCityInIgnoreCase(LocalDate.parse(date), cities, sortCondition);
        }

        return accountRepository.findAll();
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account updateAccountById(Long id, Account updatedAccountData) {
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

    public void transferMoney(Long fromAccountId, Long toAccountId, Double moneyAmount) {
        Account fromAccount = getAccount(fromAccountId);
        Account toAccount = getAccount(toAccountId);

        if (fromAccount.getAmountOfMoney() < moneyAmount) {
            throw new NotEnoughMoneyException("Not enough money");
        }

        fromAccount.decreaseAmount(moneyAmount);
        toAccount.increaseAmount(moneyAmount);

        transactionService.createTransaction(fromAccount, toAccount, moneyAmount, "transfer");

        saveAccount(fromAccount);
        saveAccount(toAccount);
    }
}
