package com.telran.bankapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date creationDate;
    private String type;
    private Integer amount;
    private long accountFromId;
    private long accountToId;

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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public long getAccountFromId() {
        return accountFromId;
    }

    public void setAccountFromId(long accountFromId) {
        this.accountFromId = accountFromId;
    }

    public long getAccountToId() {
        return accountToId;
    }

    public void setAccountToId(long accountToId) {
        this.accountToId = accountToId;
    }
}
