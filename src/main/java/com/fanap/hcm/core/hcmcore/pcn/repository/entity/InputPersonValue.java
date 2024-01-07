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
        name = "input_person_value"
)
@Entity(name = "InputPersonValue")
public class InputPersonValue {
    @Id
    @SequenceGenerator(
            name = "InputPersonValue_ID",
            sequenceName = "InputPersonValue_ID",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "InputPersonValue_ID"
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
            name = "input_parameter_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "input_person_value_input_parameter_id_fk")
    )
    private InputParameter inputParameter;

    @ManyToOne
    @JoinColumn(
            name = "input_person_transaction_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "input_person_value_input_person_transaction_id_fk")
    )
    private InputPersonTransaction inputPersonTransaction;
}
