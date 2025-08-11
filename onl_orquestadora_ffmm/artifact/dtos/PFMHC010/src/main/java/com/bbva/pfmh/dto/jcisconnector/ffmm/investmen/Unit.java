package com.bbva.pfmh.dto.jcisconnector.ffmm.investmen;


import com.bbva.pfmh.dto.jcisconnector.ffmm.attributes.NumericName;

import java.math.BigDecimal;


public class Unit extends NumericName {

    private String unitType;
    private BigDecimal percentage;


    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "unitType='" + unitType + '\'' +
                ", percentage=" + percentage +
                '}';
    }
}
