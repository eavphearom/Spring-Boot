package com.example.tutorial.service;

import com.example.tutorial.dto.Request.TestProductRequest;
import com.example.tutorial.dto.Response.TestProductResponse;
import com.example.tutorial.common.filter.BaseFilter;
import org.springframework.data.domain.Page;

public interface TestProductService {

    TestProductResponse createTestProduct(TestProductRequest request);

    TestProductResponse updateTestProductById(
            Long id,
            TestProductRequest request
    );

    boolean deleteTestProductById(Long id, String reason);

    TestProductResponse getTestProductById(Long id);

    Page<TestProductResponse> getTestProductPagination(
            BaseFilter filter
    );
}