package com.ptit.csdl.dto.request.update;

import lombok.*;
import lombok.experimental.FieldDefaults;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewUpdateRequest {
    @JsonProperty("review_id")
    Long id;
    @JsonProperty("review")
    String review;
    @JsonProperty("rating")
    String rating;
    @JsonProperty("product_id")
    Long productId;
}
