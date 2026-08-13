package com.example.tutorial.controller;

import com.example.tutorial.common.filter.BaseFilter;
import com.example.tutorial.dto.Request.TestProductRequest;
import com.example.tutorial.dto.Response.TestProductResponse;
import com.example.tutorial.response.ApiResponse;
import com.example.tutorial.service.TestProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/test-product")
public class TestProductController {

    private final TestProductService testProductService;

    @GetMapping("/pagination")
    public ApiResponse<List<TestProductResponse>> getTestProductPagination(
            BaseFilter filter
    ) {
        return ApiResponse.paginate(
                testProductService.getTestProductPagination(filter),
                filter.getPageNo()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<TestProductResponse> getTestProductById(
            @PathVariable Long id
    ) {
        return ApiResponse.success(
                testProductService.getTestProductById(id)
        );
    }

    @PostMapping
    public ApiResponse<TestProductResponse> createTestProduct(
            @Valid @ModelAttribute TestProductRequest request
    ) {
        return ApiResponse.success(
                testProductService.createTestProduct(request)
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<TestProductResponse> updateTestProductById(
            @PathVariable Long id,
            @Valid @ModelAttribute TestProductRequest request
    ) {
        return ApiResponse.success(
                testProductService.updateTestProductById(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Boolean> deleteTestProductById(
            @PathVariable Long id,
            @RequestParam(required = false) String reason
    ) {
        return ApiResponse.success(
                testProductService.deleteTestProductById(id, reason)
        );
    }
}