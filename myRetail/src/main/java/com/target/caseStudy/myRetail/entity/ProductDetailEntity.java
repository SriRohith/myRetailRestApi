package com.target.caseStudy.myRetail.entity;

import org.springframework.data.annotation.Id;

public class ProductDetailEntity {
	
	@Id
	private long id;
	private double price;
	private String currency_code;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public ProductDetailEntity(long id, double price, String currency_code) {
		super();
		this.id = id;
		this.price = price;
		this.currency_code = currency_code;
	}
	@Override
	public String toString() {
		return "ProductDetailEntity [id=" + id + ", price=" + price + ", currency_code=" + currency_code + "]";
	}
	
	
	
	
}
