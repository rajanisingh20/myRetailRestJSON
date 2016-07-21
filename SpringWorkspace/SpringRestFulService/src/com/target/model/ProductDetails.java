package com.target.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * Entity to represent a {@link ProductDetails}.
 * 
 * 
 */
@Document
public class ProductDetails {
	@Id String id ;
	String name;
	Price price;
	
	public ProductDetails(String id,String name,Price price){
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Price getPrice() {
		return price;
	}
	public void setPrice(Price price) {
		this.price = price;
	}

}
	



