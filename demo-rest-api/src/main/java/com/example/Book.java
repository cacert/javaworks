package com.example;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Book {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	private String isbn;
	private double price;

	@Override
	public String toString() {
		return "Book [name=" + name + ", isbn=" + isbn + ", price=" + price + "]";
	}
	
	
}
