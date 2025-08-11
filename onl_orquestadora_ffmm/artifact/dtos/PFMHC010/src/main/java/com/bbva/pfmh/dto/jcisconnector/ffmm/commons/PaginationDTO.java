package com.bbva.pfmh.dto.jcisconnector.ffmm.commons;

import java.io.Serializable;

public class PaginationDTO implements Serializable {
    private static final long serialVersionUID = 8268731132101006705L;
    private LinksDTO links;
    private Integer page;
    private Integer totalPages;
    private Integer totalElements;
    private Integer pageSizeOut;

    public LinksDTO getLinks() {
        return links;
    }

    public void setLinks(LinksDTO links) {
        this.links = links;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getPageSizeOut() {
        return pageSizeOut;
    }

    public void setPageSizeOut(Integer pageSizeOut) {
        this.pageSizeOut = pageSizeOut;
    }

    @Override
    public String toString() {
        return "DTOPagination{" +
                "links=" + links +
                ", page=" + page +
                ", totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                ", pageSizeOut=" + pageSizeOut +
                '}';
    }
}
