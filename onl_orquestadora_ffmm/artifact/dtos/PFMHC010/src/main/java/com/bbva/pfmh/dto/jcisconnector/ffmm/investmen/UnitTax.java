package com.bbva.pfmh.dto.jcisconnector.ffmm.investmen;

import java.io.Serializable;

public class UnitTax implements Serializable {

    private static final long serialVersionUID = -8698653869009001157L;
    private String unitType;

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    @Override
    public String toString() {
        return "UnitTax{" +
                "unitType='" + unitType + '\'' +
                '}';
    }

}
