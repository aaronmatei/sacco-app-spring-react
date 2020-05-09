package com.aaronmatei.contributionsservice.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "saving")
public class Saving {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type")
    private String type;
    @Column(name = "amount")
    private Float amount;
    @Column(name = "date_saved")
    private LocalDate dateSaved;

}
