package com.telran.bankapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Account {
    private long id;
    private String email;
    private Date creationDate;
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private Integer amount = 0;

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
}
