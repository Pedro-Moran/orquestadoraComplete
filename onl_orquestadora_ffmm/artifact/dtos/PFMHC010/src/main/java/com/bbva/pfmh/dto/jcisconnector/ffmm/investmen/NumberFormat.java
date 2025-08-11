package com.bbva.pfmh.dto.jcisconnector.ffmm.investmen;


import java.io.Serializable;


public class NumberFormat implements Serializable {

	private static final long serialVersionUID = -1771783666325289044L;
	private String number;
	private FundNumberType numberType;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public FundNumberType getNumberType() {
		return numberType;
	}

	public void setNumberType(FundNumberType numberType) {
		this.numberType = numberType;
	}

	@Override
	public String toString() {
		return "NumberFormat{" +
				"number='" + number + '\'' +
				", numberType=" + numberType +
				'}';
	}
}