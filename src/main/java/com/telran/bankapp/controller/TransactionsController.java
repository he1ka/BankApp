package com.telran.bankapp.controller;

import com.telran.bankapp.entity.Transaction;
import com.telran.bankapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionsController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionsController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * GET request to `/transactions`:
     * <p>
     * - the response code is 200
     * - the response body is an array of matching records, ordered by their ids in increasing order
     * - accepts an optional query string parameter, date, in the format YYYY-MM-DD, for example /transaction?date=2022-11-25. When this parameter is present, only the records with the matching date are returned.
     * - accepts an optional query string parameter, type, and when this parameter is present, only the records with the matching type are returned. It might contain several values, separated by commas, meaning that records with the type matching any of these values must be returned.
     * - accepts an optional query string parameter, sort, that can take one of two values: either "dateTime" or "-dateTime". If the value is "dateTime", then the ordering is by dateTime in ascending order. If it is "-dateTime", then the ordering is by dateTime in descending order. If there are two records with the same dateTime, the one with the smaller id must come first.
     */
    @GetMapping("/transactions")
    public List<Transaction> index() {
        return this.transactionService.getAllTransactions();
    }

    /**
     * GET request to `/transactions/<id>`:
     * <p>
     * - returns a record with the given id
     * - if the matching record exists, the response code is 200 and the response body is the matching object
     * - if there is no record in the collection with the given id, the response code is 404
     */
    @GetMapping("/transactions/{id}")
    public Transaction get(@PathVariable Long id) {
        return this.transactionService.getTransaction(id);
    }
}
