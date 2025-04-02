package com.ptit.csdl.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

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
    @Column(name = "house_number")
    String houseNumber;
    @Column(name = "street")
    String street;
    @Column(name = "dítrict")
    String district;
    @Column(name = "country")
    String country;
    @Column(name = "zipcode")
    String zipcode;

    @ManyToMany(mappedBy = "addresses", cascade = CascadeType.ALL)
    @JsonBackReference
    Set<Customer> customers;
}
