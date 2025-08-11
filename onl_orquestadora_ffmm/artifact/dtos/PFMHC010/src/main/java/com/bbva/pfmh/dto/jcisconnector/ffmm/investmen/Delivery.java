package com.bbva.pfmh.dto.jcisconnector.ffmm.investmen;

import java.io.Serializable;
import java.util.List;

public class Delivery implements Serializable {
    private static final long serialVersionUID = -5534772344672142181L;

    private String deliveryType;
    private String documentType;
    private List<ContactDetails> contactDetails;

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public List<ContactDetails> getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(List<ContactDetails> contactDetails) {
        this.contactDetails = contactDetails;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "deliveryType='" + deliveryType + '\'' +
                ", documentType='" + documentType + '\'' +
                ", contactDetails=" + contactDetails +
                '}';
    }
}
