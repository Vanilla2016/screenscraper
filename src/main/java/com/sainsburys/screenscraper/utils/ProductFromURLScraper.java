package com.sainsburys.screenscraper.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sainsburys.screenscraper.exceptions.ProductExtractException;
import com.sainsburys.screenscraper.exceptions.ProductAccessException;
import com.sainsburys.screenscraper.pojo.Category;
import com.sainsburys.screenscraper.pojo.Product;



public class ProductFromURLScraper {

	private String resourceURL;
	
	public ProductFromURLScraper(String resourceURL) {
		super();
		this.resourceURL = resourceURL;
	}
	
	/**
     * Fetch all products listed on products page
     * Extracts href link for each one, following each link
     * @return List of products
     * @throws ProductAccessException
     */
    public Category extractProducts() throws ProductAccessException {
        List<Product> products = new ArrayList<Product>();
        try {
            Document productsDocument = Jsoup.connect(resourceURL).get();
            Elements productLinkElements = productsDocument.select(".product h3 a");

            for (Element productLinkElement : productLinkElements) {
            	//abs:href - populates ../ in relative URLs so avoids MalformedUrlException when creating Product
                String productUri = productLinkElement.attr("abs:href");
                Product product = extractProduct(productUri);
                products.add(product);
            }
            return new Category(products);
        } catch(IOException e) {
            throw new ProductAccessException(e);
        }
    }

    /**
     * Fetch a single product
     * @param productUri pojo.Product URI
     * @return pojo.Product
     * @throws ProductAccessException
     */
    private Product extractProduct(String productUri) throws ProductAccessException {
        try {
            Connection connection = Jsoup.connect(productUri);
            Document productDocument = connection.get();//Failing here!!
            return new Product(productDocument);
        } catch(IOException e) {
            throw new ProductAccessException(e);
        } catch (ProductExtractException e) {
            throw new ProductAccessException(e);
        }
    }
	
}
