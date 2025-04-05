package com.ptit.csdl.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

import com.ptit.csdl.entity.Category;
import com.ptit.csdl.entity.ProductReview;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    String productName;
    Float price;
    Long quantityInStock;
    String vendor;
    Date createdOn;
    Date modifiedOn;
    String imageUrl;
    Set<Category> categories; // Modify
    Set<ProductReview> reviews; // modify
}
