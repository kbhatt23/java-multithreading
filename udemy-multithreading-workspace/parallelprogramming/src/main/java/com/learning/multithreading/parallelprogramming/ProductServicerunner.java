package com.learning.multithreading.parallelprogramming;

import com.learning.multithreading.parallelprogramming.products.beans.Product;
import com.learning.multithreading.parallelprogramming.products.service.IProductService;
import com.learning.multithreading.parallelprogramming.products.service.InventoryService;
import com.learning.multithreading.parallelprogramming.products.service.InventoryServiceImpl;
import com.learning.multithreading.parallelprogramming.products.service.ProductInfoService;
import com.learning.multithreading.parallelprogramming.products.service.ProductServiceCompletableFuture;
import com.learning.multithreading.parallelprogramming.products.service.ProductServiceThreads;
import com.learning.multithreading.parallelprogramming.products.service.ReviewService;

public class ProductServicerunner {
public static void main(String[] args) {
	 ReviewService reviewService = new  ReviewService();
	 ProductInfoService productInfoService = new  ProductInfoService();
	 InventoryService inventoryService = new InventoryServiceImpl();
	 
	 //sequential
	 //IProductService productService = new ProductService(productInfoService,reviewService); 
	 
	 //basic threads
	// IProductService productService = new ProductServiceThreadsBad(productInfoService,reviewService);
	 
	 //executors and future
	 //IProductService productService = new ProductServiceThreads(productInfoService,reviewService);
	 
	 //using compeltablefuture
	 
	 IProductService productService = new ProductServiceCompletableFuture(productInfoService, reviewService,inventoryService);
	 Product product = productService.retrieveProductDetails("prod123");
	 System.out.println("Product retrieved "+product);
	 
	 
} 

}
