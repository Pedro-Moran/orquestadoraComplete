package com.bbva.pfmh.dto.fmc7.response;

import com.bbva.pfmh.dto.fmc7.ffmm.FFMM7;

import java.io.Serializable;
import java.util.List;

public class FMC7Response implements Serializable {

    private static final long serialVersionUID = 3694552602349934760L;

    private List<FFMM7> ffmm7;
    private String hostAdviceCode;
    private String hostMessage;


    public List<FFMM7> getFfmm7() {
        return ffmm7;
    }

    public void setFfmm7(List<FFMM7> ffmm7) {
        this.ffmm7 = ffmm7;
    }

    public String getHostAdviceCode() {
        return hostAdviceCode;
    }

    public void setHostAdviceCode(String hostAdviceCode) {
        this.hostAdviceCode = hostAdviceCode;
    }

    public String getHostMessage() {
        return hostMessage;
    }

    public void setHostMessage(String hostMessage) {
        this.hostMessage = hostMessage;
    }

    @Override
    public String toString() {
        return "FMC7Response{" +
                "ffmm7=" + ffmm7 +
                ", hostAdviceCode='" + hostAdviceCode + '\'' +
                ", hostMessage='" + hostMessage + '\'' +
                '}';
    }
}
