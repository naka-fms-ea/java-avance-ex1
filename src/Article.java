package fr.lndr.jdbc;

public class Article {
	
	private int id;
	private String description;
	private String brand;
	private double price;
	
	/**
	 * @param id
	 * @param description
	 * @param brand
	 * @param price
	 */
	public Article(int id, String description, String brand, double price) {
		super();
		this.id = id;
		this.description = description;
		this.brand = brand;
		this.price = price;
	}
	
	public Article(String description, String brand, double price) {
		super();
		this.description = description;
		this.brand = brand;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return this.id + " - " + this.description + " - " + this.brand + " - " + this.price;
	}

	
	
	
	
}

