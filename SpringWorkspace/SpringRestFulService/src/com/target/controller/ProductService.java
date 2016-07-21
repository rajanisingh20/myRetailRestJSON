package com.target.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.target.dao.GetProductDetailsDAO;
import com.target.exception.ProductDoesNotExistException;
import com.target.model.ProductDetails;
import com.target.model.Quote;
import com.target.repository.ProductRepository;
@Component
public class ProductService implements IProductService {
	
	@Autowired
	private ProductRepository repository;
	
	
	  public ProductDetails findProductById(String itemcode) throws ProductDoesNotExistException{
	      Page<ProductDetails> product = repository.findById(itemcode);
	      return product.getContent().get(0);
	  }
	  
	  public ProductDetails findExternalProductById(String itemcode) throws ProductDoesNotExistException{
		  RestTemplate restTemplate = new RestTemplate();
		  Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class); 
		  ProductDetails product = findProductById(quote.getValue().getId().toString());
		  return product;
	  }
	  
	  public void updateProduct(ProductDetails product){
		  repository.save(product);
	  }
	    	
}
