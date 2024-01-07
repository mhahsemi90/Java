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
        name = "output_parameter",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "output_parameter_unique_code",
                        columnNames = "code"
                )
        }
)
@Entity(name = "OutputParameter")
public class OutputParameter {
    @Id
    @SequenceGenerator(
            name = "OutputParameter_ID",
            sequenceName = "OutputParameter_ID",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "OutputParameter_ID"
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

    @Column(
            name = "data_type",
            nullable = false
    )
    private String dataType;

    @Column(
            name = "formula",
            nullable = false
    )
    private String formula;

    @OneToMany(mappedBy = "outputParameter")
    private List<OutputPersonValue> outputPersonValueList = new ArrayList<>();
}
