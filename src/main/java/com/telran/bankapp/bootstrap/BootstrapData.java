package com.telran.bankapp.bootstrap;

import com.telran.bankapp.entity.Account;
import com.telran.bankapp.entity.Transaction;
import com.telran.bankapp.repository.AccountRepository;
import com.telran.bankapp.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
public class BootstrapData implements CommandLineRunner {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public BootstrapData(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Account nick = new Account();
        nick.setEmail("nick@bobrov.com");
        nick.setCreationDate(LocalDate.of(2017, 11, 11));
        nick.setFirstName("Nick");
        nick.setLastName("Bobrov");
        nick.setCountry("Germany");
        nick.setCity("Munich");
        accountRepository.save(nick);

        Account pawel = new Account();
        pawel.setEmail("pawel@bobrov.com");
        pawel.setCreationDate(LocalDate.now());
        pawel.setFirstName("Pawel");
        pawel.setLastName("Bobrov");
        pawel.setCountry("Germany");
        pawel.setCity("Berlin");
        accountRepository.save(pawel);

        Account anna = new Account();
        anna.setEmail("anna@bobrov.com");
        anna.setCreationDate(LocalDate.of(2011, 11, 11));
        anna.setFirstName("Anna");
        anna.setLastName("Bobrov");
        anna.setCountry("Germany");
        anna.setCity("Hamburg");
        anna.setAmountOfMoney(77.11);
        accountRepository.save(anna);

        Transaction first = new Transaction();
        first.setFromAccount(nick);
        first.setToAccount(pawel);
        first.setCreationDate(LocalDate.now());
        first.setType("transfer");
        first.setAmount(200.0);
        transactionRepository.save(first);

        System.out.println("Number of accounts: " + accountRepository.count());
    }
}
