package com.telran.bankapp.bootstrap;

import com.telran.bankapp.entity.Account;
import com.telran.bankapp.repository.AccountRepository;
import com.telran.bankapp.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        nick.setCreationDate(new Date());
        nick.setFirstName("Nick");
        nick.setLastName("Bobrov");
        nick.setCountry("Germany");
        nick.setCity("Munich");
        accountRepository.save(nick);

        Account pawel = new Account();
        pawel.setEmail("pawel@bobrov.com");
        pawel.setCreationDate(new Date());
        pawel.setFirstName("Pawel");
        pawel.setLastName("Bobrov");
        pawel.setCountry("Germany");
        pawel.setCity("Munich");
        accountRepository.save(pawel);

        System.out.println("Number of accounts: " + accountRepository.count());
    }
}
