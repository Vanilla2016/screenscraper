package com.sainsburys.screenscraper.serialize;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.sainsburys.screenscraper.pojo.Category;
import com.sainsburys.screenscraper.pojo.Product;

public class CategorySerializer extends StdSerializer< Category>{

	public CategorySerializer(Class<Category> t) {
		super(t);
	}

	@Override
	public void serialize(Category category, JsonGenerator jsonGenerator, 
								   SerializerProvider serializerProvider) throws IOException {
		
		jsonGenerator.writeStartObject();
		jsonGenerator.writeObjectField("results", category.getProducts());
		jsonGenerator.writeObjectFieldStart("total"); //start total object
		jsonGenerator.writeNumberField("gross", category.getTotalPrice());
		jsonGenerator.writeNumberField("VAT", category.getVAT());
		jsonGenerator.writeEndObject(); //end address object
		jsonGenerator.writeEndObject();
		
	}

}
