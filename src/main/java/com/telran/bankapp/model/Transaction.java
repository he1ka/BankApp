package com.telran.bankapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Transaction {
    private long id;
    private Date creationDate;
    private String type;
    private Integer amount;
    private long accountFromId;
    private long accountToId;
}
