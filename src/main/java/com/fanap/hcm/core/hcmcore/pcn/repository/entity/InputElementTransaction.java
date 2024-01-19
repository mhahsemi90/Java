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
        name = "input_element_transaction"
)
@Entity(name = "InputElementTransaction")
public class InputElementTransaction {
    @Id
    @SequenceGenerator(
            name = "InputElementTransaction_ID",
            sequenceName = "InputElementTransaction_ID",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "InputElementTransaction_ID"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "element_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "input_element_transaction_element_id_fk")
    )
    private Element element;

    @ManyToOne
    @JoinColumn(
            name = "calculation_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "input_element_transaction_calculation_id_fk")
    )
    private Calculation calculation;

    @OneToMany(mappedBy = "inputElementTransaction")
    private List<InputElementValue> inputElementValueList = new ArrayList<>();
}
