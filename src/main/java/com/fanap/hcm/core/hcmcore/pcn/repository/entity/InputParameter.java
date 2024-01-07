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
        name = "input_parameter",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "input_parameter_unique_code",
                        columnNames = "code"
                )
        }
)
@Entity(name = "InputParameter")
public class InputParameter {
    @Id
    @SequenceGenerator(
            name = "InputParameter_ID",
            sequenceName = "InputParameter_ID",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "InputParameter_ID"
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

    @OneToMany(mappedBy = "inputParameter")
    private List<InputPersonValue> inputPersonValueList = new ArrayList<>();
}
