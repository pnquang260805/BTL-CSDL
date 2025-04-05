package com.ptit.csdl.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.ptit.csdl.dto.request.creation.ReviewCreationRequest;
import com.ptit.csdl.dto.request.update.ReviewUpdateRequest;
import com.ptit.csdl.dto.response.ReviewResponse;
import com.ptit.csdl.entity.ProductReview;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mapping(source = "product.id", target = "productId")
    ReviewResponse toResponse(ProductReview review);
    
    @Mapping(source = "product.id", target = "productId")
    List<ReviewResponse> toResponse(List<ProductReview> review);
    ProductReview toReview(ReviewCreationRequest request);
    void updateReview(@MappingTarget ProductReview review, ReviewUpdateRequest request);

    
}
