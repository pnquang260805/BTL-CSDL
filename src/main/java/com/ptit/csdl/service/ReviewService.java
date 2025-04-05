package com.ptit.csdl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.csdl.dto.request.creation.ReviewCreationRequest;
import com.ptit.csdl.dto.request.update.ReviewUpdateRequest;
import com.ptit.csdl.dto.response.ReviewResponse;
import com.ptit.csdl.entity.Product;
import com.ptit.csdl.entity.ProductReview;
import com.ptit.csdl.exception.AppException;
import com.ptit.csdl.exception.ErrorCode;
import com.ptit.csdl.mapper.ReviewMapper;
import com.ptit.csdl.repository.ProductRepository;
import com.ptit.csdl.repository.ReviewRepository;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private ProductRepository productRepository;

    public ReviewResponse createReview(ReviewCreationRequest request) {
        ProductReview review = reviewMapper.toReview(request);
        Long productId = request.getProductId();
        Product product = productRepository.findById(productId).orElseThrow(
            () -> new AppException(ErrorCode.PRODUCT_NOT_FOUND.getMsg(), ErrorCode.PRODUCT_NOT_FOUND));
        review.setProduct(product);
        ReviewResponse response = reviewMapper.toResponse(reviewRepository.save(review));
        return response;
    }

    public List<ReviewResponse> findAllReviews(){
        List<ProductReview> review = reviewRepository.findAll();
        return reviewMapper.toResponse(review);
    }

    public String updateReview(Long review_id, ReviewUpdateRequest request){
        ProductReview review = reviewRepository.findById(review_id).orElseThrow(
            () -> new AppException(ErrorCode.PRODUCT_NOT_FOUND.getMsg(), ErrorCode.PRODUCT_NOT_FOUND));
        reviewRepository.delete(review);
        return "Success";
    }
}
