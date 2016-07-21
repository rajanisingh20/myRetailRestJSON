package com.target.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.target.controller.IProductService;
import com.target.controller.ProductDetailsController;
import com.target.model.Price;
import com.target.model.ProductDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.*;
import static org.junit.Assert.*;


public final class ProductsControllerTest {

	private static final String PRODUCT_LOCATION = "http://localhost:9080/SpringRestService/products";
	
	private static final String ITEM_LOCATION = PRODUCT_LOCATION + "/13860428" ;
	
	private ProductDetails products = new ProductDetails("13860428", "The Big Lebowski (Blu-ray) (Widescreen)", new Price(13.49,"USD"));
	
	
	@Mock
	private IProductService productService;
	
	@InjectMocks
	private ProductDetailsController productController;
	
	private MockMvc mockMvc;
	

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this); 
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
 
    }
	
	@Test
	public void getProduct()throws Exception {
		Mockito.when(this.productService.findProductById("1")).thenReturn(products);
		ResponseEntity<ProductDetails> product = productController.getProduct("13860428");
		assertEquals(product.getStatusCode(), HttpStatus.OK);
		
	}
	@Test
	public void getExternalProduct()throws Exception {
		Mockito.when(this.productService.findExternalProductById("1")).thenReturn(products);
		ResponseEntity<ProductDetails> product = productController.getProduct("13860428");
		assertEquals(product.getStatusCode(), HttpStatus.OK);
		
	}
	@Test
	public void updateProduct()throws Exception {
		
		ResponseEntity<ProductDetails> product = productController.updateProduct("13860428",new Price(13.49,"USD"));
		assertEquals(product.getStatusCode(), HttpStatus.OK);
		
	}

}
