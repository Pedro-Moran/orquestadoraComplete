package com.bbva.pfmh.dto.jcisconnector.ffmm.attributes;

import java.io.Serializable;
import java.math.BigDecimal;

public class NumericName implements Serializable {
    private static final long serialVersionUID = 2814692375729081416L;

    private BigDecimal amount;
    private String currency;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "NumericName{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
