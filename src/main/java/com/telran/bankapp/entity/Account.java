package com.telran.bankapp.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Email field should not be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "First name field should not be blank")
    private String firstName;

    @NotBlank(message = "Last name field should not be blank")
    private String lastName;

    @NotBlank(message = "Country field should not be blank")
    private String country;

    @NotBlank(message = "City field should not be blank")
    private String city;

    @Min(value = 0, message = "Amount of money should not be less than 0")
    private Double amountOfMoney = 0.0;

    private LocalDate creationDate;

    @OneToMany(mappedBy = "fromAccount", fetch = FetchType.LAZY)
    private List<Transaction> fromTransactions = new ArrayList<>();

    @OneToMany(mappedBy = "toAccount", fetch = FetchType.LAZY)
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
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

    public Double getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(Double amount) {
        this.amountOfMoney = amount;
    }

    public void setFromTransactions(List<Transaction> fromTransactions) {
        this.fromTransactions = fromTransactions;
    }

    public void setToTransactions(List<Transaction> toTransactions) {
        this.toTransactions = toTransactions;
    }

    public void increaseAmount(Double change) {
        if (amountOfMoney == null) {
            amountOfMoney = 0.0;
        }

        amountOfMoney += change;
    }

    public void decreaseAmount(Double change) {
        if (amountOfMoney == null) {
            amountOfMoney = 0.0;
        }

        amountOfMoney -= change;
    }

    public Set<Long> getTransactions()
    {
        Set<Long> result = new HashSet<>();

        for (Transaction t: fromTransactions) {
            result.add(t.getId());
        }

        for (Transaction t: toTransactions) {
            result.add(t.getId());
        }

        return result;
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
