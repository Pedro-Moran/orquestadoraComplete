package com.bbva.pfmh.dto.jcisconnector.ffmm.investmen;


import java.io.Serializable;


public class ManagerCompany implements Serializable {

	private static final long serialVersionUID = 8114477470947260095L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ManagerCompany{" +
				"name='" + name + '\'' +
				'}';
	}
}