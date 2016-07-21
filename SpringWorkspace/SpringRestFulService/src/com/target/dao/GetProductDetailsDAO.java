package com.target.dao;

import java.util.HashMap;
import java.util.Map;

import com.target.model.Price;
import com.target.model.ProductDetails;
import com.target.model.Value;



public class GetProductDetailsDAO {
	
	
	Map<String, ProductDetails> products = new HashMap<String, ProductDetails>();
	
	
	public GetProductDetailsDAO()
	{

		//product details to be retrieved from the database. 
		
		products.put("13860428", new ProductDetails("13860428", "The Big Lebowski (Blu-ray) (Widescreen)", new Price(13.49,"USD")));
	
	}
	
	public ProductDetails getProductDetails(String itemcode)
	{
		
		return products.get(itemcode);
		
	}
	
	public ProductDetails getProductDetails(Value value)
	{
		products.put(value.getId().toString(), new ProductDetails(value.getId().toString(),value.getQuote(), new Price(13.49,"USD")));
		return products.get(value.getId().toString());
		
	}
	
}
