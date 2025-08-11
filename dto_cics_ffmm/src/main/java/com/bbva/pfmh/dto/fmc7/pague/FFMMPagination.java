package com.bbva.pfmh.dto.fmc7.pague;

import java.io.Serializable;

public class FFMMPagination implements Serializable {

    private static final long serialVersionUID = 2392485252702654125L;

    private String idpagin;
    private Integer tampagi;


    public Integer getTampagi() {
        return tampagi;
    }

    public void setTampagi(Integer tampagi) {
        this.tampagi = tampagi;
    }

    public String getIdpagin() {
        return idpagin;
    }

    public void setIdpagin(String idpagin) {
        this.idpagin = idpagin;
    }

    @Override
    public String toString() {
        return "Pagination{" +
                "idpagin='" + idpagin + '\'' +
                ", tampagi=" + tampagi +
                '}';
    }
}
