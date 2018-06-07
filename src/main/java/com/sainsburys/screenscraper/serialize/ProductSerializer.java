package com.sainsburys.screenscraper.serialize;

import java.io.IOException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.sainsburys.screenscraper.pojo.Category;
import com.sainsburys.screenscraper.pojo.Product;

public class ProductSerializer extends StdSerializer<Product> {
	
	public ProductSerializer (Class t) {
		super(t);
	}
	
		@Override
		public void serialize(Product product, JsonGenerator jsonGenerator, 
									SerializerProvider serializerProvider) throws IOException {

			
			jsonGenerator.writeStartObject();
			
			jsonGenerator.writeStringField("title", product.getTitle());
			jsonGenerator.writeNumberField("kcal_per_100g", product.getKcalPer100g());
			jsonGenerator.writeNumberField("unit_price", product.getUnitPrice());
			jsonGenerator.writeStringField("description", product.getDescription());
					
			jsonGenerator.writeEndObject();
			
		}
}
