package com.example.tutorial.common.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseFilter {

    private Integer pageNo = 1;
    private Integer perPage = 10;
    private String search;
    private String sortBy = "id";
    private String sortDirection = "asc";
    private Boolean includeDeleted = false;

}