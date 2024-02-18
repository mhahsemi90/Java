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
        name = "output_element_transaction"
)
@Entity(name = "OutputElementTransaction")
public class OutputElementTransaction {
    @Id
    @SequenceGenerator(
            name = "OutputElementTransaction_ID",
            sequenceName = "OutputElementTransaction_ID",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "OutputElementTransaction_ID"
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
            foreignKey = @ForeignKey(name = "output_element_transaction_element_id_fk")
    )
    private Element element;

    @ManyToOne
    @JoinColumn(
            name = "calculation_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "output_element_transaction_calculation_id_fk")
    )
    private Calculation calculation;


    @OneToMany(mappedBy = "outputElementTransaction", cascade = CascadeType.PERSIST)
    private List<OutputElementValue> outputElementValueList = new ArrayList<>();

}
