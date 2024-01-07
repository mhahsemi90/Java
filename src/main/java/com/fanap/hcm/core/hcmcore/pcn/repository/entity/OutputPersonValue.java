package com.fanap.hcm.core.hcmcore.pcn.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "output_person_value"
)
@Entity(name = "OutputPersonValue")
public class OutputPersonValue {
    @Id
    @SequenceGenerator(
            name = "OutputPersonValue_ID",
            sequenceName = "OutputPersonValue_ID",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "OutputPersonValue_ID"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "data",
            nullable = false
    )
    private String data;

    @Column(
            name = "data_type",
            nullable = false
    )
    private String dataType;

    @ManyToOne
    @JoinColumn(
            name = "output_parameter_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "output_person_value_output_parameter_id_fk")
    )
    private OutputParameter outputParameter;

    @ManyToOne
    @JoinColumn(
            name = "output_person_transaction_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "output_person_value_output_person_transaction_id_fk")
    )
    private OutputPersonTransaction outputPersonTransaction;
}
