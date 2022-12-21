package com.telran.bankapp.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date creationDate;
    private String type;
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "from_account", nullable = false)
    private Account fromAccount;

    @ManyToOne
    @JoinColumn(name = "to_account", nullable = false)
    private Account toAccount;

    public Transaction() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getAccountFrom() {
        return fromAccount.getId();
    }

    public Long getAccountTo() {
        return toAccount.getId();
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }
}
