package com.aaronmatei.loanmanagement.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type")
    private String type;
    @Column(name = "amount")
    private Float amount;
    @Column(name = "period")
    private Float period;
    @Column(name = "purpose")
    private String purpose;
    @Column(name = "date_issued")
    private LocalDate dateIssued;


}
