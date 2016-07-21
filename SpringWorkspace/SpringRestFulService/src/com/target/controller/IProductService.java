package com.target.controller;

import com.target.exception.ProductDoesNotExistException;
import com.target.model.ProductDetails;

public interface IProductService {
	public ProductDetails findProductById(String itemcode) throws ProductDoesNotExistException;
	public ProductDetails findExternalProductById(String itemcode) throws ProductDoesNotExistException;
	public void updateProduct(ProductDetails product);
}
