package com.bbva.pfmh.dto.jcisconnector.ffmm.investmen;

import java.io.Serializable;
import java.math.BigDecimal;

public class PositionSize implements Serializable {

    private static final long serialVersionUID = -8854699584440729476L;

    private PositionType positionType;
    private PositionAmount positionAmount;
    private BigDecimal quantity;

    public PositionType getPositionType() {
        return positionType;
    }

    public void setPositionType(PositionType positionType) {
        this.positionType = positionType;
    }

    public PositionAmount getPositionAmount() {
        return positionAmount;
    }

    public void setPositionAmount(PositionAmount positionAmount) {
        this.positionAmount = positionAmount;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "PositionSize{" +
                "positionType=" + positionType +
                ", positionAmount=" + positionAmount +
                ", quantity=" + quantity +
                '}';
    }
}
