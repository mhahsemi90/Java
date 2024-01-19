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
        name = "input_element_value"
)
@Entity(name = "InputElementValue")
public class InputElementValue {
    @Id
    @SequenceGenerator(
            name = "InputElementValue_ID",
            sequenceName = "InputElementValue_ID",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "InputElementValue_ID"
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
            foreignKey = @ForeignKey(name = "input_element_value_input_parameter_id_fk")
    )
    private InputParameter inputParameter;

    @ManyToOne
    @JoinColumn(
            name = "input_element_transaction_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "input_element_value_input_element_transaction_id_fk")
    )
    private InputElementTransaction inputElementTransaction;
}
