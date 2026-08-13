package com.example.tutorial.service.impl;

import com.example.tutorial.dto.Request.TestProductRequest;
import com.example.tutorial.dto.Response.TestProductResponse;
import com.example.tutorial.entity.TestProduct;
import com.example.tutorial.exception.ResourceNotFoundException;
import com.example.tutorial.common.filter.BaseFilter;
import com.example.tutorial.repository.TestProductRepository;
import com.example.tutorial.service.TestProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestProductServiceImpl implements TestProductService {

    private final TestProductRepository testProductRepository;


    @Override
    public TestProductResponse createTestProduct(
            TestProductRequest request
    ) {
        // TODO: map request to entity

        TestProduct entity = new TestProduct();

        entity = testProductRepository.save(entity);

        return mapToResponse(entity);
    }


    @Override
    public TestProductResponse updateTestProductById(
            Long id,
            TestProductRequest request
    ) {

        TestProduct entity = testProductRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "TestProduct Not Found"
                        )
                );

        // TODO: update entity fields from request

        entity = testProductRepository.save(entity);

        return mapToResponse(entity);
    }


    @Override
    public boolean deleteTestProductById(Long id, String reason) {
        return testProductRepository.softDeleteById(id, reason);
    }


    @Override
    public TestProductResponse getTestProductById(Long id) {

        TestProduct entity = testProductRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "TestProduct Not Found"
                        )
                );

        return mapToResponse(entity);
    }


    @Override
    public Page<TestProductResponse> getTestProductPagination(
            BaseFilter filter
    ) {
        // TODO: create Pageable from BaseFilter
        // TODO: add search/filter

        throw new UnsupportedOperationException(
                "Pagination not implemented yet"
        );
    }


    private TestProductResponse mapToResponse(TestProduct entity) {

        // TODO: map entity to response

        return null;
    }
}