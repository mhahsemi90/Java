package calculation.repository.entity;

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
        name = "element_type",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "element_type_unique_code",
                        columnNames = "code"
                )
        }
)
@Entity(name = "ElementType")
public class ElementType {
    @Id
    @SequenceGenerator(
            name = "ElementType_ID",
            sequenceName = "ElementType_ID",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ElementType_ID"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "code",
            nullable = false
    )
    private String code;

    @Column(
            name = "title",
            nullable = false
    )
    private String title;

    @OneToMany(mappedBy = "elementType")
    private List<Element> elementList = new ArrayList<>();
}
