package com.sainsburys.screenscraper.pojo;

import java.math.BigDecimal;

public class Product {

	private String title;
	private BigDecimal unitPrice;
	private int kcalPer100g;
	private String description;
	
	/**
	 * 
	 * @param title
	 * @param unitPrice
	 * @param kcalPer100g
	 * @param description
	 */
	public Product(String title, BigDecimal unitPrice, int kcalPer100g, String description) {
		super();
		this.title = title;
		this.unitPrice = unitPrice;
		this.kcalPer100g = kcalPer100g;
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	
	public int getKcalPer100g() {
		return kcalPer100g;
	}
	
	public String getDescription() {
		return description;
	}

	
	
}
