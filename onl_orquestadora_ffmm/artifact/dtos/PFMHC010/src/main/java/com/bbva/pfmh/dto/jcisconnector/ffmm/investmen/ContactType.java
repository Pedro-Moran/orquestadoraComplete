package com.bbva.pfmh.dto.jcisconnector.ffmm.investmen;

import java.io.Serializable;

public class ContactType implements Serializable {

    private static final long serialVersionUID = 2320642020113155484L;

    private String id;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ContactType{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
