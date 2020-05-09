package com.aaronmatei.loanmanagement.repository;

import com.aaronmatei.loanmanagement.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
