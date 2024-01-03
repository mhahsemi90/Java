package com.fanap.hcm.core.pcn.repository.entity;

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
        name = "person",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "person_unique_vr_id",
                        columnNames = "vr_id"
                )
        }
)
@Entity(name = "Person")
public class Person {
    @Id
    @SequenceGenerator(
            name = "Person_ID",
            sequenceName = "Person_ID",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Person_ID"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "vr_id",
            columnDefinition = "TEXT",
            nullable = false
    )
    private String vrId;
}