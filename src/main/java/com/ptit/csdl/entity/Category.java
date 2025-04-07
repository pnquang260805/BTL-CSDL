package com.ptit.csdl.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "category")
public class Category {
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "category_name")
    String categoryName;
    @Column(name = "description")
    String description;
    @Column(name = "tag", unique = true)
    String tag;

    @ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL)
    @JsonBackReference
    Set<Product> products;
}
