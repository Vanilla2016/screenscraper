package com.sainsburys.screenscraper.utils;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.sainsburys.screenscraper.exceptions.ProductFetchException;
import com.sainsburys.screenscraper.pojo.Category;
import com.sainsburys.screenscraper.pojo.Product;

public class ProductFromURLScraperTest {
	
	@Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);
    private final String stubUri = "http://127.0.0.1:8089/category";
	
    /*
     * Return stubbed HTML from specified stub files
     */
    private String returnHTMLResource (String htmlStubName) throws URISyntaxException, IOException {
    	
    	htmlStubName += ".html";
    	Path catPath = Paths.get(getClass().getClassLoader().getResource(htmlStubName).toURI());
		StringBuilder catData = new StringBuilder();
		Stream<String> catLines = Files.lines(catPath);
		catLines.forEach(line -> catData.append(line).append("\n"));
		catData.deleteCharAt(catData.length()-1);
		catLines.close();
		return catData.toString();
	}
    
    @Before
	public void loadResourceAsURL() throws IOException, URISyntaxException{
    	
    	//Mock URL return as HTML stub files
    	String catHTML = returnHTMLResource("category");
				stubFor(get(urlEqualTo("/category"))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "text/html")
						.withBody(catHTML)));
				
		String strawbHTML = returnHTMLResource("strawberries");
                stubFor(get(urlEqualTo("/category/strawberries"))
        		.willReturn(aResponse()
        				.withStatus(200)
        				.withHeader("Content-Type", "text/html")
        				.withBody(strawbHTML)));
                 
        String blueHTML = returnHTMLResource("blueberries");
		         stubFor(get(urlEqualTo("/category/blueberries"))
				.willReturn(aResponse()
					.withStatus(200)
					.withHeader("Content-Type", "text/html")
					.withBody(blueHTML)));
     
        String raspHTML = returnHTMLResource("raspberries");
        stubFor(get(urlEqualTo("/category/raspberries"))
        		.willReturn(aResponse()
        				.withStatus(200)
        				.withHeader("Content-Type", "text/html")
        				.withBody(raspHTML)));

        String blackHTML = returnHTMLResource("blackberries");
        stubFor(get(urlEqualTo("/category/blackberries"))
        		.willReturn(aResponse()
        				.withStatus(200)
        				.withHeader("Content-Type", "text/html")
        				.withBody(blackHTML)));
	}
	
	/*
	 * For the purpose of testing only included 4 products links 
	 * in stubbed HTML. There are 17 on the real page
	 */
	@Test
	public void testNumberOfProductsIsFour() throws ProductFetchException {
			ProductFromURLScraper productInfoScraper = new ProductFromURLScraper(stubUri);
			Category category = productInfoScraper.extractProducts();
			assertEquals(4, category.getProducts().size());
		}
	
	/*
	 * Last out of the 4 product links
	 */
	@Test
	public void testBlackBerriesAreLast() throws ProductFetchException {
		ProductFromURLScraper productInfoScraper = new ProductFromURLScraper(stubUri);
		Category category = productInfoScraper.extractProducts();
		Product blackBerryProd = category.getProducts().get(3);
		assertEquals("Sainsbury's Blackberries, Sweet 150g", blackBerryProd.getTitle());
		assertEquals(new BigDecimal("1.50"), blackBerryProd.getUnitPrice());
		assertEquals("by Sainsbury's blackberries", blackBerryProd.getDescription());
	}
	
	
}
