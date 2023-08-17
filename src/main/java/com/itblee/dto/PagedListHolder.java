package com.itblee.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PagedListHolder<T> implements Serializable {

    public static final int DEFAULT_PAGE_SIZE = 5;

    public static final int STARTER_PAGE = 1;

    private int pageSize = DEFAULT_PAGE_SIZE;
    private int page = STARTER_PAGE;
    private List<T> source = new ArrayList<>();
    private int totalItems = 0;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (page < 1)
            throw new IllegalArgumentException("Invalid page size");
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if (page < STARTER_PAGE)
            throw new IllegalArgumentException("Invalid page");
        this.page = page;
    }

    public List<T> getSource() {
        return source;
    }

    public void setSource(List<T> source) {
        this.source = source;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        if (page < 0)
            throw new IllegalArgumentException("Invalid total size");
        this.totalItems = totalItems;
    }

    public void toLastPage() {
        int lastPage = (int) Math.ceil(totalItems / (double) pageSize);
        if (lastPage >= STARTER_PAGE)
            setPage(lastPage);
    }

    public boolean isFirstPage() {
        return page == STARTER_PAGE;
    }

    public boolean isNotFirstPage() {
        return page > STARTER_PAGE;
    }


}
