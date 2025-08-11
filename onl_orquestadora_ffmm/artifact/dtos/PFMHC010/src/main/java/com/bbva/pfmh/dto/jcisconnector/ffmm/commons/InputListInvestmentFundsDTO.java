package com.bbva.pfmh.dto.jcisconnector.ffmm.commons;

import java.io.Serializable;

public class InputListInvestmentFundsDTO implements Serializable {
    private static final long serialVersionUID = 8240869859955640058L;

    private String customerId;
    private String paginationKey;
    private Integer pageSize;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getPaginationKey() {
        return paginationKey;
    }

    public void setPaginationKey(String paginationKey) {
        this.paginationKey = paginationKey;
    }

    @Override
    public String toString() {
        return "InputListInvestmentFunds{" +
                "customerId='" + customerId + '\'' +
                ", paginationKey='" + paginationKey + '\'' +
                ", pageSize=" + pageSize +
                '}';
    }
}
