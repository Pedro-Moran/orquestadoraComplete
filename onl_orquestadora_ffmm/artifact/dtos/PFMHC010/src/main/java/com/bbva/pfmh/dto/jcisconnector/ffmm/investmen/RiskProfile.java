package com.bbva.pfmh.dto.jcisconnector.ffmm.investmen;

import java.io.Serializable;

public class RiskProfile implements Serializable {

    private static final long serialVersionUID = 1724092177488039945L;

    private String id;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RiskProfile{" +
                "id='" + id + '\'' +
                '}';
    }
}
