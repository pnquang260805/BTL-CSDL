package com.ptit.csdl.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ptit.csdl.entity.Product;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {
    @JsonProperty("category_id")
    Long id;
    @JsonProperty("category_name")
    String categoryName;
    @JsonProperty("description")
    String description;
    @JsonProperty("tag")
    String tag;
    @JsonProperty("products")
    Set<Product> products;
}
