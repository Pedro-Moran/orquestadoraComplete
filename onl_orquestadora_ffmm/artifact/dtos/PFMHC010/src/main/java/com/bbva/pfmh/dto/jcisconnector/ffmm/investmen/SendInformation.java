package com.bbva.pfmh.dto.jcisconnector.ffmm.investmen;

import java.io.Serializable;

public class SendInformation implements Serializable {

    private static final long serialVersionUID = 6023414548213743758L;
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SendInformation{" +
                "value='" + value + '\'' +
                '}';
    }
}
