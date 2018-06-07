package com.sainsburys.screenscraper.exceptions;

import java.io.IOException;

public class ProductAccessException extends Exception {
	public ProductAccessException(Exception e) {
		super(e);
	}
}
