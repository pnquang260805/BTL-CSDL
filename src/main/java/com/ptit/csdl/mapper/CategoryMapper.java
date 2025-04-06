package com.ptit.csdl.mapper;

import java.util.List;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.ptit.csdl.entity.Category;
import com.ptit.csdl.dto.request.creation.CategoryCreationRequest;
import com.ptit.csdl.dto.request.update.CategoryUpdateRequest;
import com.ptit.csdl.dto.response.CategoryResponse;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "products")
    public CategoryResponse toResponse(Category category);
    public List<CategoryResponse> toResponse(List<Category> category);
    public Category toCategory(CategoryCreationRequest request);
    public void updateCategory(@MappingTarget Category category, CategoryUpdateRequest request);
}
