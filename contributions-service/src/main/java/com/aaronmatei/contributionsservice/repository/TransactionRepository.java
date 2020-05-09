package com.aaronmatei.contributionsservice.repository;

import com.aaronmatei.contributionsservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByUserId(Long userId);
    List<Transaction> findAllBySavingId(Long savingId);
}
