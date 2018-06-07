package com.sainsburys.screenscraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sainsburys.screenscraper.exceptions.ProductAccessException;
import com.sainsburys.screenscraper.pojo.Category;
import com.sainsburys.screenscraper.utils.ProductFromURLScraper;

@SpringBootApplication
public class ScreenscraperApplication {
	/*
	 * Screen ScraperMaven application.
	 * Type mvn compile exec:java -Dexec.args="https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html"
	 */
	public static void main(String[] args) throws ProductAccessException, JsonProcessingException {
		SpringApplication.run(ScreenscraperApplication.class, args);
		String productsUri = args[0];
		ProductFromURLScraper productScraper = new ProductFromURLScraper(productsUri);
        Category category = productScraper.extractProducts();
        System.out.println(category.toJSON());
	}
}
