package com.fanap.hcm.core.hcmcore.pcn.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "calculation"
)
@Entity(name = "Calculation")
public class Calculation {
    @Id
    @SequenceGenerator(
            name = "Calculation_ID",
            sequenceName = "Calculation_ID",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Calculation_ID"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "action_date",
            nullable = false
    )
    private Date actionDate;

    @OneToMany(mappedBy = "calculation")
    private List<OutputElementTransaction> outputElementTransactionList = new ArrayList<>();

    @OneToMany(mappedBy = "calculation")
    private List<InputElementTransaction> inputElementTransactionList = new ArrayList<>();
}
