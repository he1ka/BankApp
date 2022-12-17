package com.telran.bankapp.controller;

import com.telran.bankapp.model.Transaction;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TransactionsController {
    private final List<Transaction> transactions = new ArrayList<>();

    @GetMapping("/transactions")
    public List<Transaction> index() {
        return transactions;
    }

    @GetMapping("/transactions/{id}")
    public Transaction get(@PathVariable String id) {
        for (Transaction transaction : transactions) {
            if (transaction.getId() == Integer.parseInt(id)) {
                return transaction;
            }
        }

       return null;
    }
}
