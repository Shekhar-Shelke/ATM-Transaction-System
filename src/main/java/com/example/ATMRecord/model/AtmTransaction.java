package com.example.ATMRecord.model;

import jakarta.persistence.*;

@Entity
@Table(name = "atm_transaction")
public class AtmTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private String transactionType; // DEPOSIT or WITHDRAW

    @Column(nullable = false)
    private Double amount;

    private Double atmCashBalance;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getTransactionType() { return transactionType; }
    public void setTransactionType(String transactionType) { this.transactionType = transactionType; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public Double getAtmCashBalance() { return atmCashBalance; }
    public void setAtmCashBalance(Double atmCashBalance) { this.atmCashBalance = atmCashBalance; }
}
