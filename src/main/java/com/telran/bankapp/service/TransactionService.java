package com.telran.bankapp.service;

import com.telran.bankapp.entity.Account;
import com.telran.bankapp.entity.Transaction;
import com.telran.bankapp.exception.TransactionNotFoundException;
import com.telran.bankapp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction getTransaction(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException("id = " + id));
    }

    public Transaction createTransaction(Account fromAccount, Account toAccount, Double moneyAmount, String type)
    {
        Transaction transaction = new Transaction();
        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);
        transaction.setAmount(moneyAmount);
        transaction.setType(type);
        transaction.setCreationDate(LocalDate.now());

        return saveTransaction(transaction);
    }

}
