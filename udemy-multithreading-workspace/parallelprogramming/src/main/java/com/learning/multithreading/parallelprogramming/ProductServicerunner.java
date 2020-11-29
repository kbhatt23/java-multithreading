package com.learning.multithreading.parallelprogramming;

import com.learning.multithreading.parallelprogramming.products.beans.Product;
import com.learning.multithreading.parallelprogramming.products.service.IProductService;
import com.learning.multithreading.parallelprogramming.products.service.ProductInfoService;
import com.learning.multithreading.parallelprogramming.products.service.ProductServiceThreads;
import com.learning.multithreading.parallelprogramming.products.service.ReviewService;

public class ProductServicerunner {
public static void main(String[] args) {
	 ReviewService reviewService = new  ReviewService();
	 ProductInfoService productInfoService = new  ProductInfoService();
	 
	 //IProductService productService = new ProductService(productInfoService,reviewService); 
	 
	// IProductService productService = new ProductServiceThreadsBad(productInfoService,reviewService);
	 
	 IProductService productService = new ProductServiceThreads(productInfoService,reviewService);
	 
	 Product product = productService.retrieveProductDetails("prod123");
	 System.out.println("Product retrieved "+product);
	 
	 
} 

}
