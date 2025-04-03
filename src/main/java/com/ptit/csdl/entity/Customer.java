package com.ptit.csdl.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "customers")
public class Customer {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "customer_name")
    String customerName;

    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;
    @Column(name = "phone_number")
    String phoneNumber;
    @Column(name = "gender")
    String gender;
    @Column(name = "email")
    String email;
    @Column(name = "password")
    String password;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "customer_address",
            // Khóa phức hợp
            joinColumns = @JoinColumn(name = "customer_id"), // Xác định cột trong bảng trung gian đại diện cho khóa chính của Customer.
            inverseJoinColumns = @JoinColumn(name = "address_id") // Xác định cột đại diện cho khóa chính của Address
    )
    @JsonManagedReference
    Set<Address> addresses; // mappedBy tên biến này

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    @JsonManagedReference
    Set<Order> orders;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    @JsonManagedReference
    Set<PaymentAccount> paymentAccounts = new HashSet<>();

    // Các helper method để duy trì mối quan hệ hai chiều
    public void addAddress(Address address){
        this.addresses.add(address);
        address.getCustomers().add(this);
    }
}
