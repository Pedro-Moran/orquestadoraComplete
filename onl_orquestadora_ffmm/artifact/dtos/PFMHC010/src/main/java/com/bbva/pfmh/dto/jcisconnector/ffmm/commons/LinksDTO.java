package com.bbva.pfmh.dto.jcisconnector.ffmm.commons;

import java.io.Serializable;

public class LinksDTO implements Serializable {

    private static final long serialVersionUID = 4845434800549142797L;

    private  String first;
    private  String last;
    private  String previous;
    private  String next;

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    @Override
    public String toString() {
        return "DTOLinks{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", previous='" + previous + '\'' +
                ", next='" + next + '\'' +
                '}';
    }
}
