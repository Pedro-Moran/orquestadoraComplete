package com.bbva.pfmh.dto.jcisconnector.ffmm.investmen;

import java.io.Serializable;
import java.math.BigDecimal;

public class Factor implements Serializable {

    private static final long serialVersionUID = -3511274291256225942L;

    private BigDecimal value;
    private String ratio;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    @Override
    public String toString() {
        return "Factor{" +
                "value=" + value +
                ", ratio='" + ratio + '\'' +
                '}';
    }
}
