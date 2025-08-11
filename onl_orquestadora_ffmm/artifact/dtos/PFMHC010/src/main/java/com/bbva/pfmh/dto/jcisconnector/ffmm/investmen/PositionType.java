package com.bbva.pfmh.dto.jcisconnector.ffmm.investmen;

import java.io.Serializable;

public class PositionType implements Serializable {

    private static final long serialVersionUID = -5038373011215303829L;

    private String id;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PositionType{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
