package com.target.exception;

public class ProductDoesNotExistException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message = null;

	public ProductDoesNotExistException(){super();}
	public ProductDoesNotExistException(String mesage){
		super();
		this.message=message;
	}
@Override
	public String toString(){
		return message;
	}
}
