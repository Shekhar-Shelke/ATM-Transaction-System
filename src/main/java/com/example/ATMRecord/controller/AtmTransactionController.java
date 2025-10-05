package com.example.ATMRecord.controller;

import com.example.ATMRecord.model.AtmTransaction;
import com.example.ATMRecord.repository.AtmTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class AtmTransactionController {

    @Autowired
    private AtmTransactionRepository repository;

    // Initial ATM cash balance
    private double atmCashBalance = 1000000.0;

    // Create transaction (ATM cash updated only here)
    @PostMapping
    public AtmTransaction createTransaction(@RequestBody AtmTransaction transaction) {
        if (transaction.getTransactionType().equalsIgnoreCase("DEPOSIT")) {
            atmCashBalance += transaction.getAmount();
        } else if (transaction.getTransactionType().equalsIgnoreCase("WITHDRAW")) {
            atmCashBalance -= transaction.getAmount();
        }

        transaction.setAtmCashBalance(atmCashBalance);
        return repository.save(transaction);
    }

    // Get all transactions
    @GetMapping
    public List<AtmTransaction> getAllTransactions() {
        return repository.findAll();
    }

    // Get transaction by ID
    @GetMapping("/{id}")
    public ResponseEntity<AtmTransaction> getTransactionById(@PathVariable Long id) {
        Optional<AtmTransaction> transaction = repository.findById(id);
        return transaction.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update transaction (does NOT affect ATM cash)
    @PutMapping("/{id}")
    public ResponseEntity<AtmTransaction> updateTransaction(
            @PathVariable Long id,
            @RequestBody AtmTransaction updatedTransaction) {

        return repository.findById(id)
                .map(transaction -> {
                    transaction.setAccountNumber(updatedTransaction.getAccountNumber());
                    transaction.setTransactionType(updatedTransaction.getTransactionType());
                    transaction.setAmount(updatedTransaction.getAmount());
                    return ResponseEntity.ok(repository.save(transaction));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete transaction (does NOT affect ATM cash)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Get current ATM cash balance
    @GetMapping("/atm-balance")
    public double getAtmBalance() {
        return atmCashBalance;
    }
}
