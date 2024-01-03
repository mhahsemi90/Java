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
@Table
@Entity(name = "Person")
public class Person {
    @Id
    @SequenceGenerator(
            name = "Person_ID",
            sequenceName = "Person_ID",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Person_ID"
    )
    private Long id;
    private String vrId;
}

