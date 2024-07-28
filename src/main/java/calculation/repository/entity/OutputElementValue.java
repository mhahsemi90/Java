package calculation.repository.entity;

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
        name = "output_element_value"
)
@Entity(name = "OutputElementValue")
public class OutputElementValue {
    @Id
    @SequenceGenerator(
            name = "OutputElementValue_ID",
            sequenceName = "OutputElementValue_ID",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "OutputElementValue_ID"
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
            foreignKey = @ForeignKey(name = "output_element_value_output_parameter_id_fk")
    )
    private OutputParameter outputParameter;

    @ManyToOne
    @JoinColumn(
            name = "output_element_transaction_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "output_element_value_output_element_transaction_id_fk")
    )
    private OutputElementTransaction outputElementTransaction;

    @ManyToOne
    @JoinColumn(
            name = "formula_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "output_element_value_formula_id_fk")
    )
    private Formula formula;
}
