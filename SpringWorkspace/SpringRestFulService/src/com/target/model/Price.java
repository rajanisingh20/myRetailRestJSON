package com.target.model;

@Document
public class Price {
@Id
     private long priceId;
	double value;
	String currency_code;
	
	public Price (double value, String currency_code){
		this.value = value;
		this.currency_code = currency_code;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getCurrency_code() {
		return currency_code;
	}
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}
	
}
