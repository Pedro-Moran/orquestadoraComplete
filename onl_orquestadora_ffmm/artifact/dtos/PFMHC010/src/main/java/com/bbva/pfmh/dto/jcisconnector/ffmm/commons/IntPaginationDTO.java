package com.bbva.pfmh.dto.jcisconnector.ffmm.commons;

import java.io.Serializable;

public class IntPaginationDTO implements Serializable {
    private static final long serialVersionUID = 1146873641065093348L;

    private String paginationKey;
    private Long pageSize;

    public String getPaginationKey() {
        return paginationKey;
    }

    public void setPaginationKey(String paginationKey) {
        this.paginationKey = paginationKey;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "IntPagination{" +
                "paginationKey='" + paginationKey + '\'' +
                ", pageSize=" + pageSize +
                '}';
    }
}
