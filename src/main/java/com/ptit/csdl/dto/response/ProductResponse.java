package com.ptit.csdl.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ptit.csdl.entity.Category;
import com.ptit.csdl.entity.ProductReview;
import com.ptit.csdl.entity.Supplier;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    @JsonProperty("product_id")
    Long id;
    String productName;
    Float price;
    Long quantityInStock;
    Date createdOn;
    Date modifiedOn;
    String imageUrl;
    Set<Category> categories; // Modify
    Set<ProductReview> reviews; // modify
    @JsonProperty("supplier_id")
    Long supplierId;
    @JsonProperty("supplier_name")
    String supplierName;
}
