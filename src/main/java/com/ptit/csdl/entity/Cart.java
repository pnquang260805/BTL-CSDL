package com.ptit.csdl.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "create_at")
    Date createAt;

    @Column(name = "update_at")
    Date updateAt;

    @OneToOne(cascade = CascadeType.ALL)
    Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    Set<CartProduct> cartProducts;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    Set<Order> orders;
}
