package com.payAm.core.dto;

/**
 * Created by Payam Mostafaei
 * Creation time: 2017/Aug/14 - 1:56 PM
 */
public class PaginationDto {
    private int pageNumber;
    private int pageSize;

    public int getPageNumber() {
        return pageNumber;
    }
    public void setPageNumber(int pageNumber) {
        if (pageNumber <= 0) {
            pageNumber = 1;
        }
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
