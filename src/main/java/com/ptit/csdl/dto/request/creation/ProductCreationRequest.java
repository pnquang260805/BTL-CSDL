package com.ptit.csdl.dto.request.creation;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreationRequest {
    String productName;
    Float price;
    Long quantityInStock;
    String vendor;
    Date createdOn;
    Date modifiedOn;
    String imageUrl;
    Set<Long> categoryId;
}
