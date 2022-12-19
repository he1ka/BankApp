package com.telran.bankapp.controller;

import com.telran.bankapp.entity.Transaction;
import com.telran.bankapp.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionsController {
    private final TransactionService transactionService;

    public TransactionsController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public List<Transaction> index() {
        return this.transactionService.getAllTransactions();
    }

    @GetMapping("/transactions/{id}")
    public Transaction get(@PathVariable String id) {
        return this.transactionService.getTransaction(Long.parseLong(id));
    }
}
