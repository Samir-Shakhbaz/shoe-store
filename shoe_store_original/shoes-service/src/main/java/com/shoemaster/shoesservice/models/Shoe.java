package com.shoemaster.shoesservice.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Shoe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shoeId;

    @Column(nullable = false)
    String description;

    @Column(nullable = false)
    String make;

    @Column(nullable = false)
    Float size;

}
