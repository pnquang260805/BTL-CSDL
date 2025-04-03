package com.ptit.csdl.dto.request.creation;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryCreationRequest {
    String categoryName;
    String description;
    String tag;
    Set<Long> productId;
}
