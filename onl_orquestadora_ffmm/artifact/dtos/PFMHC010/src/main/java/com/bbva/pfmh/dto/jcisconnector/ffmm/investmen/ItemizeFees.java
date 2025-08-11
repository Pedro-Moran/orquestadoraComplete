package com.bbva.pfmh.dto.jcisconnector.ffmm.investmen;


import java.io.Serializable;

public class ItemizeFees implements Serializable {

    private static final long serialVersionUID = 3000866776291736187L;

    private String feeType;

    private String description;

    private Unit unit;

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "ItemizeFees{" +
                "feeType='" + feeType + '\'' +
                ", description='" + description + '\'' +
                ", unit=" + unit +
                '}';
    }
}
