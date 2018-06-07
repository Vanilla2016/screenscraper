package com.sainsburys.screenscraper.pojo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.sainsburys.screenscraper.serialize.CategorySerializer;
import com.sainsburys.screenscraper.serialize.ProductSerializer;

public class Category {
	
	final BigDecimal vat = new BigDecimal(20);
	protected List<Product> products;

	public Category(List<Product> products) {
		super();
		this.products = products;
	}

	public List<Product> getProducts() {
		return products;
	} 
	 
	/**
     * @return total unit price of all products
     * What about the VAT?
     */
    public BigDecimal getTotalPrice() {
        BigDecimal total = new BigDecimal(0);
        for(Product product : products) {
            total = total.add(product.getUnitPrice());
        }
        return total;
    }
    
    public BigDecimal getVAT() {
    	
    	//System.out.println(getTotalPrice());
    	BigDecimal vatAmount = getTotalPrice().divide(new BigDecimal(100));
    	//System.out.println("vatAmount :" +vatAmount);
    	//System.out.println("finalVatAmount :" +vatAmount.multiply(vat).setScale(2, RoundingMode.CEILING));
    	return vatAmount.multiply(vat).setScale(2, RoundingMode.CEILING);
    	//return new BigDecimal(getTotalPrice()/100*20);
    }
    
    /*
     * Return formatted JSON String of product 
     * details
     */
    public String toJSON () throws JsonProcessingException {
	
		SimpleModule module = new SimpleModule();
		module.addSerializer(new CategorySerializer(Category.class));
		module.addSerializer(new ProductSerializer(Product.class));
		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = mapper.registerModule(module)
			  .writer(new DefaultPrettyPrinter())
			  .writeValueAsString(this);
		return jsonResult;
    }
}
