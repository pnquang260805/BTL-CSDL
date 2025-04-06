package com.ptit.csdl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.csdl.dto.request.creation.CategoryCreationRequest;
import com.ptit.csdl.dto.request.update.CategoryUpdateRequest;
import com.ptit.csdl.dto.response.CategoryResponse;
import com.ptit.csdl.entity.Category;
import com.ptit.csdl.exception.AppException;
import com.ptit.csdl.exception.ErrorCode;
import com.ptit.csdl.mapper.CategoryMapper;
import com.ptit.csdl.repository.CategoryRepository;
import com.ptit.csdl.repository.ProductRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductRepository productRepository;

    public CategoryResponse createCategory(CategoryCreationRequest request) {
        Category category = categoryMapper.toCategory(request);
        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    public List<CategoryResponse> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toResponse(categories);
    }

    public List<CategoryResponse> findCategoryByTag(String tag) {
        return categoryMapper.toResponse(categoryRepository.findByTag(tag));
    }

    public CategoryResponse updateCategory(Long id, CategoryUpdateRequest request) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.CATEGORY_NOT_FOUND.getMsg(), ErrorCode.CATEGORY_NOT_FOUND));
        categoryMapper.updateCategory(category, request);
        return categoryMapper.toResponse(categoryRepository.findById(id).orElseThrow(
            () -> new AppException(ErrorCode.CATEGORY_NOT_FOUND.getMsg(), ErrorCode.CATEGORY_NOT_FOUND)));
    }

    public List<CategoryResponse> deleteCategory(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.CATEGORY_NOT_FOUND.getMsg(), ErrorCode.CATEGORY_NOT_FOUND));
        categoryRepository.delete(category);
        return getAllCategory();
    }
}
