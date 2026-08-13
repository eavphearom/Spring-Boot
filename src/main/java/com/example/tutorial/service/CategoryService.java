package com.example.tutorial.service;

import com.example.tutorial.dto.Request.CategoryRequest;
import com.example.tutorial.dto.Response.CategoryResponse;
import com.example.tutorial.common.filter.BaseFilter;
import org.springframework.data.domain.Page;

public interface CategoryService {

    CategoryResponse createCategory(CategoryRequest request);

    CategoryResponse updateCategoryById(
            Long id,
            CategoryRequest request
    );

    boolean deleteCategoryById(Long id, String reason);

    CategoryResponse getCategoryById(Long id);

    Page<CategoryResponse> getCategoryPagination(
            BaseFilter filter
    );
}