package com.aaronmatei.contributionsservice.service;

import com.aaronmatei.contributionsservice.model.Saving;
import com.aaronmatei.contributionsservice.model.Transaction;

import java.util.List;

public interface SavingService {
    List<Saving> allSavings();

    Saving findSavingById(Long savingId);

    List<Transaction> findSavingsByUserId(Long userId);

    List<Transaction> findSavingsBySavingsId(Long savingsId);

    Transaction saveTransaction(Transaction transaction);
}
