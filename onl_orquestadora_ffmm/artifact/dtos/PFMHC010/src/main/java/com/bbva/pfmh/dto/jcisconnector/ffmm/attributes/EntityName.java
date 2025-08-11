package com.bbva.pfmh.dto.jcisconnector.ffmm.attributes;

import java.io.Serializable;

public class EntityName implements Serializable {
    private static final long serialVersionUID = -8059619502256046198L;

    private String id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "NameEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
