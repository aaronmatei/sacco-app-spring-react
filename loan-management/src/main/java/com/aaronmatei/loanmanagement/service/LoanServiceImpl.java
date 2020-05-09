package com.aaronmatei.loanmanagement.service;

import com.aaronmatei.loanmanagement.model.Loan;
import com.aaronmatei.loanmanagement.model.Transaction;
import com.aaronmatei.loanmanagement.repository.LoanRepository;
import com.aaronmatei.loanmanagement.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService{
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Loan> allLoans(){
        return loanRepository.findAll();
    }
    @Override
    public Loan findLoanById(Long loanId){
        return loanRepository.findById(loanId).orElse(null);
    }
    @Override
    public List<Transaction> findTransactionsOfUser(Long userId){
        return transactionRepository.findAllByUserId(userId);
    }
    @Override
    public List<Transaction> findTransactionsByLoanId(Long loanId){
        return transactionRepository.findAllByLoanId(loanId);
    }
    @Override
    public Transaction saveTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }
}
