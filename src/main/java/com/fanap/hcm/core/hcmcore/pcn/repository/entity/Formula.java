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
        name = "formula",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "formula_unique_code_and_version",
                        columnNames = {"code", "version"}
                )
        }
)
@Entity(name = "Formula")
public class Formula {
    @Id
    @SequenceGenerator(
            name = "Formula_ID",
            sequenceName = "Formula_ID",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Formula_ID"
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
            name = "version",
            nullable = false
    )
    private Long version;

    @Column(
            name = "formula",
            nullable = false
    )
    private String formula;

    @OneToMany(mappedBy = "formula")
    private List<OutputElementValue> outputElementValueList = new ArrayList<>();
}
