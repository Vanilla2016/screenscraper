package com.sainsburys.screenscraper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sainsburys.screenscraper.pojo.Product;
import com.sainsburys.screenscraper.utils.ProductInfoScraper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScreenscraperApplicationTests {

	protected ProductInfoScraper productInfoScraper;
	protected String testResourceUrll;
	
	
	 @Test 
	public void testFileResourcesAvailable () {
	 
	try {
			String expectedString = "Hello World from fileTest.txt!!!";
			Path path = Paths.get(getClass().getClassLoader().getResource("fileTest.txt").toURI());
			StringBuilder data = new StringBuilder();
			Stream<String> lines = Files.lines(path);
			lines.forEach(line -> data.append(line).append("\n"));
			data.deleteCharAt(data.length()-1);
			lines.close();
			assertEquals(expectedString, data.toString());
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testProductConstructorAndGetters () {
		
		String productTitle = "Sainsbury's Strawberries 400g";
		BigDecimal productUnitprice = new BigDecimal(1.75);		
		int productKcalPer100g = 33;
		String productDescription = "Sainsbury's Strawberries 400g";
		
		Product product = new Product(productTitle, productUnitprice, productKcalPer100g, productDescription);
		assertNotNull(product);	
		assertEquals(productTitle, product.getTitle());
		assertEquals(productUnitprice, product.getUnitPrice());
		assertEquals(productKcalPer100g, product.getKcalPer100g());
		assertEquals(productDescription, product.getDescription());
	}
	
	@Test
	public void testJsoupDoc(){
		
		String html = "<html><head><title>First parse</title></head>"
				  + "<body><p>Parsed HTML into a doc.</p></body></html>";
		Document parsedDocument =  Jsoup.parse(html);
	}
	
	@Test
	public void testProductInfoScraper() {
		
		productInfoScraper = new ProductInfoScraper(testResourceUrll);
	}
}
