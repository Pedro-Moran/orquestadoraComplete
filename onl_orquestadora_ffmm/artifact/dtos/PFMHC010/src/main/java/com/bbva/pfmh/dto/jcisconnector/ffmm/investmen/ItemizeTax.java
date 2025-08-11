package com.bbva.pfmh.dto.jcisconnector.ffmm.investmen;


import java.io.Serializable;

public class ItemizeTax implements Serializable {

    private static final long serialVersionUID = -3085458021444082019L;

    private String taxType;

    private String description;

    private UnitTax unit;

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UnitTax getUnit() {
        return unit;
    }

    public void setUnit(UnitTax unit) {
        this.unit = unit;
    }


    @Override
    public String toString() {
        return "ItemizeTax{" +
                "taxType='" + taxType + '\'' +
                ", description='" + description + '\'' +
                ", unit=" + unit +
                '}';
    }
}
