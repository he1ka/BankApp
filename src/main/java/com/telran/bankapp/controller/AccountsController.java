package com.telran.bankapp.controller;

import com.telran.bankapp.entity.Account;
import com.telran.bankapp.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AccountsController {
   // private final List<Account> accounts = new ArrayList<>();

    private AccountService accountService;

    public AccountsController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public List<Account> index() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/accounts/{id}")
    public Account get(@PathVariable String id) {
        return accountService.getAccount(Long.parseLong(id)); //getAccount(Integer.parseInt(id));
    }

    @PostMapping("/accounts")
    public Account create(@RequestBody Account account) {
        return accountService.saveAccount(account);
    }

    @PatchMapping("/accounts/{id}")
    public Account update(@PathVariable String id, @RequestBody Account account) {
//        for (int i = 0; i < accounts.size(); i++) {
//            if (accounts.get(i).getId() == Long.parseLong(id)) {
//                account.setId(Long.parseLong(id));
//                accounts.set(i, account);
//
//                return account;
//            }
//        }

       return null;
    }

    @PutMapping("/accounts")
    public void transferMoney(@RequestParam Integer from, @RequestParam Integer to, @RequestParam Integer amount) {
        Account fromAccount = getAccount(from);
        Account toAccount = getAccount(to);

        if (fromAccount == null || toAccount == null) {
            throw new RuntimeException("Not found");
        }

        if (fromAccount.getAmount() >= amount) {
            fromAccount.decreaseAmount(amount);
            toAccount.increaseAmount(amount);
            // @todo add transaction
        } else {
            throw new RuntimeException("Not enough money");
        }

        return;
    }

    private Account getAccount(Integer id) {
//        for (Account account : accounts) {
//            if (account.getId() == id) {
//                return account;
//            }
//        }

        return null;
    }
}
