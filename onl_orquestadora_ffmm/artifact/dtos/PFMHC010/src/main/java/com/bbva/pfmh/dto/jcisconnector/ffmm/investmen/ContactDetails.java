package com.bbva.pfmh.dto.jcisconnector.ffmm.investmen;

import java.io.Serializable;

public class ContactDetails implements Serializable {

    private static final long serialVersionUID = 7460981160567461524L;

    private ContactDetail contactDetail;

    public ContactDetail getContactDetail() {
        return contactDetail;
    }

    public void setContactDetail(ContactDetail contactDetail) {
        this.contactDetail = contactDetail;
    }

    @Override
    public String toString() {
        return "ContactDetails{" +
                "contactDetail=" + contactDetail +
                '}';
    }
}
