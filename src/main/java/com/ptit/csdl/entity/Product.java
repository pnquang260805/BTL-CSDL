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
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    Long id;
    @Column(name = "product_name")
    String productName;
    @Column(name = "quantity_in_stock")
    Long quantityInStock;
    @Column(name = "created_on")
    Date createdOn;
    @Column(name = "modified_on")
    Date modifiedOn;
    @Column(name = "image_url")
    String imageUrl;

    Float price;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "category_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @JsonManagedReference
    Set<Category> categories = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product") // Tên trùng với biến product ở bên kia
    @JsonManagedReference
    Set<ProductReview> reviews; // Cùng tên với response

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    Set<CartProduct> cartProducts; // nhớ phải cùng tên với response
}
