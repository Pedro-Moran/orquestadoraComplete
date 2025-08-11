package com.bbva.pfmh.dto.jcisconnector.ffmm.investmen;

import java.io.Serializable;

public class ContactDetail implements Serializable {
    private static final long serialVersionUID = 218280369322472713L;

    private String contactDetailId;
    private String id;
    private String contact;
    private ContactType contactType;

    public String getContactDetailId() {
        return contactDetailId;
    }

    public void setContactDetailId(String contactDetailId) {
        this.contactDetailId = contactDetailId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    @Override
    public String toString() {
        return "ContactDetail{" +
                "contactDetailId='" + contactDetailId + '\'' +
                ", id='" + id + '\'' +
                ", contact='" + contact + '\'' +
                ", contactType=" + contactType +
                '}';
    }
}
