package com.ptit.csdl.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewResponse {
    @JsonProperty("review_id")
    Long id;
    @JsonProperty("review")
    String review;
    @JsonProperty("rating")
    String rating;
    @JsonProperty("product_id")
    Long productId;
}