package com.sep.pricemanagement.model;

import java.io.Serializable;

public class SasaTemp implements Serializable{

	private static final long serialVersionUID = 2900354878441453697L;
	private String text;
	private Long broj;

	public SasaTemp() {
		
	}
	
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public Long getBroj() {
		return broj;
	}
	public void setBroj(Long broj) {
		this.broj = broj;
	}
	
}
