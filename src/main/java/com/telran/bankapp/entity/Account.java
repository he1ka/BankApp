package com.telran.bankapp.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private Date creationDate;
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private Integer amount = 0;

    @OneToMany(mappedBy = "fromAccount", fetch = FetchType.EAGER)
    private List<Transaction> fromTransactions = new ArrayList<>();
    @OneToMany(mappedBy = "toAccount", fetch = FetchType.EAGER)
    private List<Transaction> toTransactions = new ArrayList<>();

    public Account() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public List<Transaction> getFromTransactions() {
        return fromTransactions;
    }

    public void setFromTransactions(List<Transaction> fromTransactions) {
        this.fromTransactions = fromTransactions;
    }

    public List<Transaction> getToTransactions() {
        return toTransactions;
    }

    public void setToTransactions(List<Transaction> toTransactions) {
        this.toTransactions = toTransactions;
    }

    public void increaseAmount(int change) {
        if (amount == null) {
            amount = 0;
        }

        amount += change;
    }

    public void decreaseAmount(int change) {
        if (amount == null) {
            amount = 0;
        }

        amount -= change;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account book = (Account) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
