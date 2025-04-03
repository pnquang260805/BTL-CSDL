package com.ptit.csdl.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    Long id;
    String houseNumber;
    String street;
    String district;
    String country;
    String zipcode;

    @ManyToMany(mappedBy = "addresses", cascade = CascadeType.ALL)
    @JsonBackReference
    Set<Customer> customers = new HashSet<>();
}
