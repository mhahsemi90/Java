package com.fanap.hcm.core.hcmcore.pcn.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "output_person_transaction"
)
@Entity(name = "OutputPersonTransaction")
public class OutputPersonTransaction {
    @Id
    @SequenceGenerator(
            name = "OutputPersonTransaction_ID",
            sequenceName = "OutputPersonTransaction_ID",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "OutputPersonTransaction_ID"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "person_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "output_person_transaction_person_id_fk")
    )
    private Person person;

    @ManyToOne
    @JoinColumn(
            name = "calculation_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "output_person_transaction_calculation_id_fk")
    )
    private Calculation calculation;


    @OneToMany(mappedBy = "outputPersonTransaction")
    private List<OutputPersonValue> outputPersonValueList = new ArrayList<>();

}
