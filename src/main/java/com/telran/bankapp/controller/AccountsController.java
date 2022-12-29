package com.telran.bankapp.controller;

import com.telran.bankapp.entity.Account;
import com.telran.bankapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountsController {
    private final AccountService accountService;

    @Autowired
    public AccountsController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * GET request to `/accounts`:
     * - the response code is 200
     * - the response body is an array of matching records, ordered by their ids in increasing order
     * - accepts an optional query string parameter, date, in the format YYYY-MM-DD, for example /account?date=2022-11-25. When this parameter is present, only the records with the matching date are returned.
     * - accepts an optional query string parameter, city, and when this parameter is present, only the records with the matching city are returned. The value of this parameter is case insensitive, so "London" and "london" are equivalent. Moreover, it might contain several values, separated by commas (e.g. city=london,Munich), meaning that records with the city matching any of these values must be returned.
     * - accepts an optional query string parameter, sort, that can take one of two values: either "creationDate" or "-creationDate". If the value is "creationDate", then the ordering is by date in ascending order. If it is "-creationDate", then the ordering is by creationDate in descending order. If there are two records with the same creationDate, the one with the smaller id must come first.
     */
    @GetMapping("/accounts")
    public List<Account> index(
            @RequestParam(required = false) String date,
            @RequestParam(required = false) List<String> city,
            @RequestParam(required = false) String sort
    ) {
        return accountService.getAllAccounts(date, city, sort);
    }

    /**
     * GET request to `/accounts/<id>`:
     * - returns a record with the given id
     * - if the matching record exists, the response code is 200 and the response body is the matching object
     * - if there is no record in the collection with the given id, the response code is 404
     */
    @GetMapping("/accounts/{id}")
    public Account get(@PathVariable Long id) {
        return accountService.getAccount(id);
    }

    /**
     * POST request to `/accounts`:
     * - creates a new account data record
     * - expects a valid account data object as its body payload, except that it does not have an id property
     * - adds the given object to the collection and assigns a unique long id to it
     * - the response code is 201 and the response body is the created record, including its unique id
     */
    @PostMapping("/accounts")
    public Account create(@RequestBody Account account) {
        return accountService.saveAccount(account);
    }

    /**
     * PATCH request to `/accounts/<id>`:
     * - updates an account with the given id
     * - if the matching record exists, the response code is 200 and the response body is the updated object
     * - if there is no record in the collection with the given id, the response code is 404
     */
    @PatchMapping("/accounts/{id}")
    public Account update(@PathVariable Long id, @RequestBody Account account) {
        return accountService.updateAccountById(id, account);
    }

    /**
     * PUT request to `/accounts?from=<fromId>&to=<toId>&amount=<moneyAmount>`:
     * - transfers money between accounts with given ids
     * - if the matching records exists and account has enough money to transfer, the response code is 200 and the empty response body
     * - if there is no record(s) in the collection with the given id, the response code is 404 with error(s) explanation in response
     * - if account has not enough money to transfer, the response code is 400 with error explanation in response
     */
    @PutMapping("/accounts")
    public void transferMoney(@RequestParam Long from, @RequestParam Long to, @RequestParam Double amount) {
        accountService.transferMoney(from, to, amount);
    }
}
