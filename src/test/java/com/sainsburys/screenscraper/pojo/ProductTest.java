package com.sainsburys.screenscraper.pojo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;

public class ProductTest {

	@Test
	public void testProductConstructorAndGetters () {
		
		String productTitle = "Sainsbury's Strawberries 400g";
		BigDecimal productUnitprice = new BigDecimal(1.75);		
		int productKcalPer100g = 33;
		String productDescription = "by Sainsbury's Strawberries";
		
		Product product = new Product(productTitle, productUnitprice, productKcalPer100g, productDescription);
		assertNotNull(product);	
		assertEquals(productTitle, product.getTitle());
		assertEquals(productUnitprice, product.getUnitPrice());
		assertEquals(productKcalPer100g, product.getKcalPer100g());
		assertEquals(productDescription, product.getDescription());
	}
	
}
