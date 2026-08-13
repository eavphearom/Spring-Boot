package com.example.tutorial.service.impl;

import com.example.tutorial.dto.Request.CategoryRequest;
import com.example.tutorial.dto.Response.CategoryResponse;
import com.example.tutorial.entity.Category;
import com.example.tutorial.exception.ResourceNotFoundException;
import com.example.tutorial.common.filter.BaseFilter;
import com.example.tutorial.repository.CategoryRepository;
import com.example.tutorial.service.CategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;


    @Override
    public CategoryResponse createCategory(
            CategoryRequest request
    ) {
        // TODO: map request to entity

        Category entity = new Category();

        entity = categoryRepository.save(entity);

        return mapToResponse(entity);
    }


    @Override
    public CategoryResponse updateCategoryById(
            Long id,
            CategoryRequest request
    ) {

        Category entity = categoryRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category Not Found"
                        )
                );

        // TODO: update entity fields from request

        entity = categoryRepository.save(entity);

        return mapToResponse(entity);
    }


    @Override
    public boolean deleteCategoryById(Long id, String reason) {
        return categoryRepository.softDeleteById(id, reason);
    }


    @Override
    public CategoryResponse getCategoryById(Long id) {

        Category entity = categoryRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category Not Found"
                        )
                );

        return mapToResponse(entity);
    }


    @Override
    public Page<CategoryResponse> getCategoryPagination(
            BaseFilter filter
    ) {
        // TODO: create Pageable from BaseFilter
        // TODO: add search/filter

        throw new UnsupportedOperationException(
                "Pagination not implemented yet"
        );
    }


    private CategoryResponse mapToResponse(Category entity) {

        // TODO: map entity to response

        return null;
    }
}