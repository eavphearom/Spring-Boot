package com.example.tutorial.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @SequenceGenerator(
            name = "categories_seq",
            sequenceName = "categories_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "categories_seq"
    )
    @Id
    private Long id;
}