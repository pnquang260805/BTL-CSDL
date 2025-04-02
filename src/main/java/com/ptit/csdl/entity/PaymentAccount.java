package com.ptit.csdl.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "payment_account")
public class PaymentAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_account_id")
    Long id;

    @Column(name = "account_number")
    String accountNumber;
    @Column(name = "provider")
    String provider;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paymentAccount")
    @JsonManagedReference
    Set<Order> orders;
}
