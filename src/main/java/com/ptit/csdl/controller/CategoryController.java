package com.ptit.csdl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.csdl.dto.request.creation.CategoryCreationRequest;
import com.ptit.csdl.dto.request.update.CategoryUpdateRequest;
import com.ptit.csdl.dto.response.CategoryResponse;
import com.ptit.csdl.entity.Category;
import com.ptit.csdl.exception.ApiResponse;
import com.ptit.csdl.mapper.CategoryMapper;
import com.ptit.csdl.service.CategoryService;

@RestController
@RequestMapping(value = "/api/categories")
public class CategoryController {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ApiResponse<CategoryResponse> createCategory(@RequestBody CategoryCreationRequest request){
        ApiResponse<CategoryResponse> response = new ApiResponse<>();
        CategoryResponse categoryResponse = categoryService.createCategory(request);
        response.setResult(categoryResponse);
        return response;
    }

    @GetMapping
    public ApiResponse<List<CategoryResponse>> getAllCategories(){
        ApiResponse<List<CategoryResponse>> response = new ApiResponse<>();
        List<CategoryResponse> categoryResponse = categoryService.getAllCategory();
        response.setResult(categoryResponse);
        return response;
    }

    @GetMapping(params = "tag")
    public ApiResponse<List<CategoryResponse>> getCategoryByTag(@RequestParam String tag){
        ApiResponse<List<CategoryResponse>> response = new ApiResponse<>();
        List<CategoryResponse> categoryResponse = categoryService.getAllCategory();
        response.setResult(categoryResponse);
        return response;
    }

    @PutMapping(params = "id")
    public ApiResponse<CategoryResponse> modifyCategory(@RequestParam Long id, @RequestBody CategoryUpdateRequest request){
        ApiResponse<CategoryResponse> response = new ApiResponse<>();
        CategoryResponse categoryResponse = categoryService.updateCategory(id, request);
        response.setResult(categoryResponse);
        return response;
    }

    @DeleteMapping(params = "id")
    public ApiResponse<List<CategoryResponse>> deleteCategory(@RequestParam Long id){
        ApiResponse<List<CategoryResponse>> response = new ApiResponse<>();
        List<CategoryResponse> categoryResponse = categoryService.deleteCategory(id);
        response.setResult(categoryResponse);
        return response;
    }
}
