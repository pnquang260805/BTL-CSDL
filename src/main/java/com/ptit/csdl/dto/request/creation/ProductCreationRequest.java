package com.ptit.csdl.dto.request.creation;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreationRequest {
    @JsonProperty("product_name")
    String productName;
    @JsonProperty("price")
    Float price;
    @JsonProperty("quantity_in_stock")
    Long quantityInStock;
    @JsonProperty("created_on")
    Date createdOn;
    @JsonProperty("modified_on")
    Date modifiedOn;
    @JsonProperty("image_url")
    String imageUrl;
    @JsonProperty("category_ids")
    Set<Long> categoryIds;
    @JsonProperty("review_ids")
    Set<Long> reviewIds;
    @JsonProperty("supplier_id")
    Long supplierId;
}
