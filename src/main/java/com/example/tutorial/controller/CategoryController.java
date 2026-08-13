package com.example.tutorial.controller;

import com.example.tutorial.common.filter.BaseFilter;
import com.example.tutorial.dto.Request.CategoryRequest;
import com.example.tutorial.dto.Response.CategoryResponse;
import com.example.tutorial.response.ApiResponse;
import com.example.tutorial.service.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/pagination")
    public ApiResponse<List<CategoryResponse>> getCategoryPagination(
            BaseFilter filter
    ) {
        return ApiResponse.paginate(
                categoryService.getCategoryPagination(filter),
                filter.getPageNo()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<CategoryResponse> getCategoryById(
            @PathVariable Long id
    ) {
        return ApiResponse.success(
                categoryService.getCategoryById(id)
        );
    }

    @PostMapping
    public ApiResponse<CategoryResponse> createCategory(
            @Valid @ModelAttribute CategoryRequest request
    ) {
        return ApiResponse.success(
                categoryService.createCategory(request)
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<CategoryResponse> updateCategoryById(
            @PathVariable Long id,
            @Valid @ModelAttribute CategoryRequest request
    ) {
        return ApiResponse.success(
                categoryService.updateCategoryById(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Boolean> deleteCategoryById(
            @PathVariable Long id,
            @RequestParam(required = false) String reason
    ) {
        return ApiResponse.success(
                categoryService.deleteCategoryById(id, reason)
        );
    }
}