package com.its.CURD.entity;



import java.util.List;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Producer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Producer> products; // One-to-Many relationship

    // Constructors
    public Category() {}
    public Category(String name) {
        this.name = name;
    }

    // Getters and Setters
}
