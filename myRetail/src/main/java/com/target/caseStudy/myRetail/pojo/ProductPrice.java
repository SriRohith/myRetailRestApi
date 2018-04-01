package com.target.caseStudy.myRetail.pojo;

public class ProductPrice {
	
	private double price;
	private String currency_code;
	

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCurrency_code() {
		return currency_code;
	}
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}
	public ProductPrice(double price, String currency_code) {
		super();
		this.price = price;
		this.currency_code = currency_code;
	}
	public ProductPrice() {
		// TODO Auto-generated constructor stub
	}

	
}
