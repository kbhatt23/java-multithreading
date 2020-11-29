package com.learning.multithreading.parallelprogramming.products.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.learning.multithreading.parallelprogramming.products.beans.Product;
import com.learning.multithreading.parallelprogramming.products.beans.ProductInfo;
import com.learning.multithreading.parallelprogramming.products.beans.Review;
import com.learning.multithreading.parallelprogramming.util.CommonUtil;

public class ProductServiceThreads  implements IProductService{

	private ProductInfoService productInfoService;
	private ReviewService reviewService;
	

	public ProductServiceThreads(ProductInfoService productInfoService, ReviewService reviewService) {
		this.productInfoService = productInfoService;
		this.reviewService = reviewService;
	}

	public Product retrieveProductDetails(String productId) {
		CommonUtil.resetTimer();
		CommonUtil.startTimer();
		//ExecutorService service = Executors.newFixedThreadPool(2);
		ExecutorService service = Executors.newCachedThreadPool();
		
		Future<ProductInfo> productInfoFuture = service.submit(() -> productInfoService.retrieveProductInfo(productId));
		Future<Review> reviewFuture = service.submit(() -> reviewService.retrieveReview(productId));
		
		Review review = null;
		ProductInfo productInfo=null;
		try {
			productInfo = productInfoFuture.get();
			review = reviewFuture.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		service.shutdownNow();
		Product product = new Product(productId, productInfo, review);
		CommonUtil.stopTimer();
		CommonUtil.timeTaken();
		return product;
	}

}
