package com.bbva.pfmh.dto.jcisconnector.ffmm.investmen;


import java.io.Serializable;
import java.util.List;

public class TaxesSubscription implements Serializable {

    private static final long serialVersionUID = -3820060642667107978L;

    private List<ItemizeTax> itemizeTaxes;

    public List<ItemizeTax> getItemizeTaxes() {
        return itemizeTaxes;
    }

    public void setItemizeTaxes(List<ItemizeTax> itemizeTaxes) {
        this.itemizeTaxes = itemizeTaxes;
    }

    @Override
    public String toString() {
        return "TaxesSubscription{" +
                "itemizeTaxes=" + itemizeTaxes +
                '}';
    }
}
