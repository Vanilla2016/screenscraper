package com.sainsburys.screenscraper.pojo;

import java.math.BigDecimal;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import com.sainsburys.screenscraper.exceptions.ProductExtractException;

public class Product {

	private String title;
	private BigDecimal unitPrice;
	private int kcal_per_100g;
	private String description;
	
	/**
	 * 
	 * @param title
	 * @param unitPrice
	 * @param kcalPer100g
	 * @param description
	 */
	public Product(String title, BigDecimal unitPrice, int kcal_per_100g, String description) {
		super();
		this.title = title;
		this.kcal_per_100g = kcal_per_100g;
		this.unitPrice = unitPrice;
		this.description = description;
	}
	
	/*
	 * Constructor taking JSoup Document as parameter
	 * Extracts Body Element, then calls methods to extract 
	 * required properties from it  kcal_per_100g????
	 */
	public Product(Document productDocument) throws ProductExtractException {
		Element bodyElement = productDocument.body();
		title = extractTitle(bodyElement);
		kcal_per_100g = extractKcalPer100g(bodyElement);
		unitPrice = extractUnitPrice(bodyElement);
		description = extractDescription(bodyElement);
	}
	
	private String extractTitle(Element productElement) throws ProductExtractException {
		Element titleElement = productElement.select("h1").first();
        if(titleElement != null) {
            return titleElement.text();
        } else {
            throw new ProductExtractException("Error parsing title");
        }
	}
	
	private int extractKcalPer100g (Element productElement) throws ProductExtractException {
		Element tableRowElement = productElement.select(".tableRow0").first();
		if(tableRowElement != null) {
			for (Element element : tableRowElement.children()) {
				if (element.hasText())
					if (element.text().contains("kcal"))
						return Integer.parseInt(element.text().replace("kcal", ""));
			}
		}
		return 0;
	}
	
	private BigDecimal extractUnitPrice(Element productElement) throws ProductExtractException {
        Element unitPriceElement = productElement.select(".pricePerUnit").first();
        if(unitPriceElement != null) {
            String unitPriceString = unitPriceElement.text().replace("£", "").replace("/unit", "");
            return new BigDecimal(unitPriceString);
        } else {
            throw new ProductExtractException("Error parsing unit price");
        }
    }
	
	private String extractDescription(Element productElement) throws ProductExtractException {
        Element titleElement = productElement.select(".productText p").first();
        if(titleElement != null) {
            return titleElement.text();
        } else {
            throw new ProductExtractException("Error parsing description");
        }
    }

	public String getTitle() {
		return title;
	}
	
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	
	public int getKcalPer100g() {
		return kcal_per_100g;
	}
	
	public String getDescription() {
		return description;
	}
	
}
