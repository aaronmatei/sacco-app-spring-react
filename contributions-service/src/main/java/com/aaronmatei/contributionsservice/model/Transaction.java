package com.aaronmatei.contributionsservice.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "saving_id",referencedColumnName = "id")
    private Saving saving;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "date_of_saving")
    private LocalDateTime dateOfSaving;
}

