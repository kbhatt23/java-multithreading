package com.learning.multithreading.parallelprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.RepeatedTest;

import com.learning.multithreading.parallelprogramming.products.beans.Product;
import com.learning.multithreading.parallelprogramming.products.service.IProductService;
import com.learning.multithreading.parallelprogramming.products.service.ProductInfoService;
import com.learning.multithreading.parallelprogramming.products.service.ProductService;
import com.learning.multithreading.parallelprogramming.products.service.ProductServiceCompletableFuture;
import com.learning.multithreading.parallelprogramming.products.service.ProductServiceThreads;
import com.learning.multithreading.parallelprogramming.products.service.ProductServiceThreadsBad;
import com.learning.multithreading.parallelprogramming.products.service.ReviewService;

class ProductServicerunnerTest {
	ReviewService reviewService = new ReviewService();
	ProductInfoService productInfoService = new ProductInfoService();

	@RepeatedTest(5)
	void completableFuture() {
		// using compeltablefuture
		IProductService productService = new ProductServiceCompletableFuture(productInfoService, reviewService);
		String productId = "prod123";
		Product product = productService.retrieveProductDetails(productId);
		assertNotNull(product);
		assertEquals(productId, product.getProductId());
	}

	@RepeatedTest(5)
	void sequential() {

		// sequential
		IProductService productService = new ProductService(productInfoService, reviewService);

		String productId = "prod123";
		Product product = productService.retrieveProductDetails(productId);
		assertNotNull(product);
		assertEquals(productId, product.getProductId());
	}

	@RepeatedTest(5)
	void basicThreads() {

		IProductService productService = new ProductServiceThreadsBad(productInfoService, reviewService);

		String productId = "prod123";
		Product product = productService.retrieveProductDetails(productId);
		assertNotNull(product);
		assertEquals(productId, product.getProductId());
	}

	@RepeatedTest(5)
	void executors() {
		// executors and future
		IProductService productService = new ProductServiceThreads(productInfoService, reviewService);
		String productId = "prod123";
		Product product = productService.retrieveProductDetails(productId);
		assertNotNull(product);
		assertEquals(productId, product.getProductId());
	}

}
