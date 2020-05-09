package com.aaronmatei.loanmanagement.service;

import com.aaronmatei.loanmanagement.model.Loan;
import com.aaronmatei.loanmanagement.model.Transaction;

import java.util.List;

public interface LoanService {
    List<Loan> allLoans();
    Loan findLoanById(Long loanId);
    List<Transaction> findTransactionsOfUser(Long userId);
    List<Transaction> findTransactionsByLoanId(Long loanId);
    Transaction saveTransaction(Transaction transaction);
}
