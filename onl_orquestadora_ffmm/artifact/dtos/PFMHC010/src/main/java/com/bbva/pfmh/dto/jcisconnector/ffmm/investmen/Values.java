package com.bbva.pfmh.dto.jcisconnector.ffmm.investmen;

import java.io.Serializable;

public class Values implements Serializable {


    private static final long serialVersionUID = 4597059006334457301L;

    private Factor factor;

    private String exchangeType;

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public Factor getFactor() {
        return factor;
    }

    public void setFactor(Factor factor) {
        this.factor = factor;
    }


    @Override
    public String toString() {
        return "Values{" +
                "factor=" + factor +
                ", exchangeType='" + exchangeType + '\'' +
                '}';
    }
}
