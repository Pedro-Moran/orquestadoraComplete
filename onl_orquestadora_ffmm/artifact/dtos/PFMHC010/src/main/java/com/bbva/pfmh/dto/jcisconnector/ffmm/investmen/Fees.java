package com.bbva.pfmh.dto.jcisconnector.ffmm.investmen;


import java.io.Serializable;
import java.util.List;

public class Fees implements Serializable {

    private static final long serialVersionUID = -5299959971295224591L;

    private List<ItemizeFees> itemizeFees;

    public List<ItemizeFees> getItemizeFees() {
        return itemizeFees;
    }

    public void setItemizeFees(List<ItemizeFees> itemizeFees) {
        this.itemizeFees = itemizeFees;
    }

    @Override
    public String toString() {
        return "Fees{" +
                "itemizeFees=" + itemizeFees +
                '}';
    }
}
