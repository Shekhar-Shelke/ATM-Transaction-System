package com.example.ATMRecord.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ATMRecord.model.AtmTransaction;

public interface AtmTransactionRepository extends JpaRepository<AtmTransaction, Long> {
}
