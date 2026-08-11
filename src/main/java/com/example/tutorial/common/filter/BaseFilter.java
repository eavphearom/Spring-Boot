package com.example.tutorial.common.filter;

public class BaseFilter {

    private int pageNo = 1;
    private int perPage = 10;
    private String search;
    private String sortBy = "id";
    private String sortDirection = "desc";

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPerPage() {
        return perPage;
    }
    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortBy() {
        return sortBy;
    }
    public void setSortDirection(String sortDirection) {
        this.sortDirection = BaseFilter.this.sortDirection;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}