package com.example.tutorial.response;

public class Pagination {
    private int perPage;
    private long total;
    private int totalPage;
    private int pageNo;

    public Pagination(int perPage, long total, int totalPage, int pageNo) {
        this.perPage = perPage;
        this.total = total;
        this.totalPage = totalPage;
        this.pageNo = pageNo;
    }

    public int getPerPage() {
        return perPage;
    }

    public long getTotal() {
        return total;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getPageNo() {
        return pageNo;
    }
}
