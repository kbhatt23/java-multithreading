package com.learning.multithreading.parallelprogramming.products.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import com.learning.multithreading.parallelprogramming.products.beans.Product;
import com.learning.multithreading.parallelprogramming.products.beans.ProductInfo;
import com.learning.multithreading.parallelprogramming.products.beans.ProductOption;
import com.learning.multithreading.parallelprogramming.products.beans.Review;
import com.learning.multithreading.parallelprogramming.util.CommonUtil;

public class ProductServiceCompletableFuture  implements IProductService{

	private ProductInfoService productInfoService;
	private ReviewService reviewService;
	private InventoryService inventoryService;
	

	public ProductServiceCompletableFuture(ProductInfoService productInfoService, ReviewService reviewService) {
		this.productInfoService = productInfoService;
		this.reviewService = reviewService;
	}
	public ProductServiceCompletableFuture(ProductInfoService productInfoService, ReviewService reviewService, InventoryService inventoryService) {
		this.productInfoService = productInfoService;
		this.reviewService = reviewService;
		this.inventoryService=inventoryService;
	}

	public Product retrieveProductDetails(String productId) {
		CommonUtil.resetTimer();
		CommonUtil.startTimer();
		CompletableFuture<Review> reviewFuture = CompletableFuture.supplyAsync( () ->  reviewService.retrieveReview(productId));
		CompletableFuture<Product> productFuture= 
				CompletableFuture.supplyAsync(() -> productInfoService.retrieveProductInfo(productId))
						 .thenCombine(reviewFuture, (productInfo,review) -> new Product(productId, productInfo, review));
		;
		
		//we can still make use of invenotry service here
		//dependent task
		Product productRes = productFuture.thenApply(product -> {
			List<ProductOption> propductOptions = product.getProductInfo().getPropductOptions().parallelStream()
						//.sequential()
							.map(option -> {
								option.setInventory(inventoryService.retrieveInventory(option.getOptionId()));
								return option;
							}).collect(Collectors.toList());
		
			product.getProductInfo().setPropductOptions(propductOptions);
			return product;
		}).join();
		
		CommonUtil.stopTimer();
		CommonUtil.timeTaken();
		return productRes;
	}

}
