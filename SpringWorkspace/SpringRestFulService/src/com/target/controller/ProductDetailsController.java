package com.target.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.target.exception.IllegalTransitionException;
import com.target.exception.ProductDoesNotExistException;
import com.target.model.Price;
import com.target.model.ProductDetails;

@Controller
@RequestMapping("/products")
public class ProductDetailsController {
	
	private final IProductService service;
	
	@Autowired
	public ProductDetailsController(IProductService service){
		this.service = service;
	}

	/* Perform an HTTP GET to retrieve the product name from an external API.
	 * Read pricing information from a NoSQL data store.
	 * Combine pricing information and product id into a single response.
	 */
	 @RequestMapping(method =  RequestMethod.GET, value="/{itemcode}", produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<ProductDetails> getExternalProduct(@PathVariable String itemcode) throws ProductDoesNotExistException{
		 ProductDetails product = this.service.findExternalProductById(itemcode); 
		 if(product == null){
	    		return new ResponseEntity<ProductDetails>(HttpStatus.NOT_FOUND);
	     }
	     return new ResponseEntity<ProductDetails>(product,HttpStatus.OK);
     }
    
     /* Responds to an HTTP GET request at /products/{id} and delivers product data as JSON.
      * 
      * 
      */
    @RequestMapping(method =  RequestMethod.GET, value="/{itemcode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDetails> getProduct(@PathVariable String itemcode) throws ProductDoesNotExistException{
    	ProductDetails product = this.service.findProductById(itemcode);   
    	if(product == null){
    		return new ResponseEntity<ProductDetails>(HttpStatus.NOT_FOUND);
    	}
    	return new ResponseEntity<ProductDetails>(product,HttpStatus.OK);
          
    }
    
    /*Accepts an HTTP PUT request at /products/{id}.
     * Contain a JSON request body
     * Update product's price in the data store.
     */
    
    @RequestMapping(method = RequestMethod.PUT, value = "/{itemcode}", consumes = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<ProductDetails> updateProduct(@PathVariable String itemcode, @RequestBody Price price)throws ProductDoesNotExistException
    {    
    	ProductDetails currentProduct = this.service.findProductById(itemcode); 
    	if(currentProduct == null){
    		return new ResponseEntity<ProductDetails>(HttpStatus.NOT_FOUND);
    	}
    	Price newPrice = new Price (price.getValue() , price.getCurrency_code()); 	
     	currentProduct.setPrice(newPrice);
     	this.service.updateProduct(currentProduct);
     	return new ResponseEntity<ProductDetails>(currentProduct,HttpStatus.OK);
    
    }
 
  
  @ExceptionHandler({ProductDoesNotExistException.class})
  ResponseEntity<String>handleNotFound(Exception e){
	  return new ResponseEntity<String>(String.format("{ \"reason\": \"%s\"}",e.getMessage()), HttpStatus.NOT_FOUND);
  }
  
  @ExceptionHandler({IllegalTransitionException.class})
  ResponseEntity<String>handleBadRequest(Exception e){
	  return new ResponseEntity<String>(String.format("{ \"reason\": \"%s\"}",e.getMessage()), HttpStatus.BAD_REQUEST);
  }
}