package com.bbva.pfmh.dto.fmc7.request;

import java.io.Serializable;

public class FMC7Request implements Serializable {

    private static final long serialVersionUID = -8903513692058605901L;


    private String numCli;
    private String indPagi;
    private Integer tamPagi;

    public String getNumCli() {
        return numCli;
    }

    public void setNumCli(String numCli) {
        this.numCli = numCli;
    }

    public String getIndPagi() {
        return indPagi;
    }

    public void setIndPagi(String indPagi) {
        this.indPagi = indPagi;
    }

    public Integer getTamPagi() {
        return tamPagi;
    }

    public void setTamPagi(Integer tamPagi) {
        this.tamPagi = tamPagi;
    }

    public static final class Builder {
        private FMC7Request fmc7Request;

        private Builder() {
            fmc7Request = new FMC7Request();
        }

        public static Builder an() {
            return new Builder();
        }

        public Builder withNumCli(String numCli) {
            fmc7Request.setNumCli(numCli);
            return this;
        }

        public Builder withIndPagi(String indPagi) {
            fmc7Request.setIndPagi(indPagi);
            return this;
        }

        public Builder withTamPagi(Integer tamPagi) {
            fmc7Request.setTamPagi(tamPagi);
            return this;
        }

        public FMC7Request build() {
            return fmc7Request;
        }
    }


}
