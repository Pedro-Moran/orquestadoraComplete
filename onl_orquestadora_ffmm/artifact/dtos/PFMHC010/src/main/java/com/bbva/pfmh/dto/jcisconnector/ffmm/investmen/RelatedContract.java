package com.bbva.pfmh.dto.jcisconnector.ffmm.investmen;

import java.io.Serializable;

public class RelatedContract implements Serializable {

    private static final long serialVersionUID = 4495555542673468475L;

    private String relatedContractId;


    public String getRelatedContractId() {
        return relatedContractId;
    }

    public void setRelatedContractId(String relatedContractId) {
        this.relatedContractId = relatedContractId;
    }

    @Override
    public String toString() {
        return "RelatedContract{" +
                "relatedContractId='" + relatedContractId + '\'' +
                '}';
    }
}
