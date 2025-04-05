package com.ptit.csdl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.csdl.dto.request.creation.ReviewCreationRequest;
import com.ptit.csdl.dto.response.ReviewResponse;
import com.ptit.csdl.exception.ApiResponse;
import com.ptit.csdl.service.ReviewService;

@RestController
@RequestMapping(value = "/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ApiResponse<ReviewResponse> createReview(@RequestBody ReviewCreationRequest request){
        ApiResponse<ReviewResponse> response = new ApiResponse<>();
        ReviewResponse review = reviewService.createReview(request);
        response.setResult(review);
        return response;
    }

    @GetMapping
    public ApiResponse<List<ReviewResponse>> getAllReviews(){
        ApiResponse<List<ReviewResponse>> response = new ApiResponse<>();
        List<ReviewResponse> review = reviewService.findAllReviews();
        response.setResult(review);
        return response;
    } 
}
