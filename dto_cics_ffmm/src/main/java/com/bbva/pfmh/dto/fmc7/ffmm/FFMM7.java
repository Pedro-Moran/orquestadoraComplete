package com.bbva.pfmh.dto.fmc7.ffmm;

import java.io.Serializable;
import java.math.BigDecimal;

public class FFMM7 implements Serializable {

    private static final long serialVersionUID = 284017254984810989L;


    private String idContr;
    private BigDecimal numCuot;
    private BigDecimal salDisp;
    private String dMonEsd;
    private String idSubPr;
    private String dSubPro;
    private String idMonFn;
    private BigDecimal salCont;
    private BigDecimal valCuot;
    private String cTipNum;
    private String dTipNum;


    public String getIdContr() {
        return idContr;
    }

    public void setIdContr(String idContr) {
        this.idContr = idContr;
    }

    public BigDecimal getNumCuot() {
        return numCuot;
    }

    public void setNumCuot(BigDecimal numCuot) {
        this.numCuot = numCuot;
    }

    public BigDecimal getSalDisp() {
        return salDisp;
    }

    public void setSalDisp(BigDecimal salDisp) {
        this.salDisp = salDisp;
    }

    public String getdMonEsd() {
        return dMonEsd;
    }

    public void setdMonEsd(String dMonEsd) {
        this.dMonEsd = dMonEsd;
    }

    public String getdSubPro() {
        return dSubPro;
    }

    public void setdSubPro(String dSubPro) {
        this.dSubPro = dSubPro;
    }

    public BigDecimal getSalCont() {
        return salCont;
    }

    public void setSalCont(BigDecimal salCont) {
        this.salCont = salCont;
    }

    public String getcTipNum() {
        return cTipNum;
    }

    public void setcTipNum(String cTipNum) {
        this.cTipNum = cTipNum;
    }

    public String getdTipNum() {
        return dTipNum;
    }

    public void setdTipNum(String dTipNum) {
        this.dTipNum = dTipNum;
    }

    public BigDecimal getValCuot() {
        return valCuot;
    }

    public void setValCuot(BigDecimal valCuot) {
        this.valCuot = valCuot;
    }

    public String getIdMonFn() {
        return idMonFn;
    }

    public void setIdMonFn(String idMonFn) {
        this.idMonFn = idMonFn;
    }

    public String getIdSubPr() {
        return idSubPr;
    }

    public void setIdSubPr(String idSubPr) {
        this.idSubPr = idSubPr;
    }

    @Override
    public String toString() {
        return "FFMM7{" +
                "idContr='" + idContr + '\'' +
                ", numCuot=" + numCuot +
                ", salDisp=" + salDisp +
                ", dMonEsd='" + dMonEsd + '\'' +
                ", idSubPr='" + idSubPr + '\'' +
                ", dSubPro='" + dSubPro + '\'' +
                ", idMonFn='" + idMonFn + '\'' +
                ", salCont=" + salCont +
                ", valCuot=" + valCuot +
                ", cTipNum='" + cTipNum + '\'' +
                ", dTipNum='" + dTipNum + '\'' +
                '}';
    }
}