package com.aaronmatei.contributionsservice.service;

import com.aaronmatei.contributionsservice.model.Saving;
import com.aaronmatei.contributionsservice.model.Transaction;
import com.aaronmatei.contributionsservice.repository.SavingRepository;
import com.aaronmatei.contributionsservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavingServiceImpl implements SavingService {
    @Autowired
    private SavingRepository savingRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Saving> allSavings(){
        return savingRepository.findAll();
    }
    @Override
    public Saving findSavingById(Long savingId){
        return savingRepository.findById(savingId).orElse(null);
    }
    @Override
    public List<Transaction> findSavingsByUserId(Long userId){
        return transactionRepository.findAllByUserId(userId);
    }
    @Override
    public List<Transaction> findSavingsBySavingsId(Long savingsId){
        return transactionRepository.findAllBySavingId(savingsId);
    }
    @Override
    public Transaction saveTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

}
