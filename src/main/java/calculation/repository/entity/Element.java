package calculation.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "element",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "element_unique_vr_id_and_element_type",
                        columnNames = {"vr_id", "element_type_id"}
                )
        }
)
@Entity(name = "Element")
public class Element {
    @Id
    @SequenceGenerator(
            name = "Element_ID",
            sequenceName = "Element_ID",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Element_ID"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "vr_id",
            nullable = false
    )
    private String vrId;

    @ManyToOne
    @JoinColumn(
            name = "element_type_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "element_element_type_id_fk")
    )
    private ElementType elementType;

    @OneToMany(mappedBy = "element")
    private List<OutputElementTransaction> outputElementTransactionList = new ArrayList<>();

    @OneToMany(mappedBy = "element")
    private List<InputElementTransaction> inputElementTransactionList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return Objects.equals(id, element.id) && Objects.equals(vrId, element.vrId) && Objects.equals(outputElementTransactionList, element.outputElementTransactionList) && Objects.equals(inputElementTransactionList, element.inputElementTransactionList);
    }
}