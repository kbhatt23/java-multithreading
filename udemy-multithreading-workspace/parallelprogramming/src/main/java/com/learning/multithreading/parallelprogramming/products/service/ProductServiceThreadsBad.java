package com.learning.multithreading.parallelprogramming.products.service;

import com.learning.multithreading.parallelprogramming.products.beans.Product;
import com.learning.multithreading.parallelprogramming.products.beans.ProductInfo;
import com.learning.multithreading.parallelprogramming.products.beans.Review;
import com.learning.multithreading.parallelprogramming.util.CommonUtil;

public class ProductServiceThreadsBad  implements IProductService{

	private ProductInfoService productInfoService;
	private ReviewService reviewService;
	

	public ProductServiceThreadsBad(ProductInfoService productInfoService, ReviewService reviewService) {
		this.productInfoService = productInfoService;
		this.reviewService = reviewService;
	}

	public Product retrieveProductDetails(String productId) {
		CommonUtil.resetTimer();
		CommonUtil.startTimer();
		ProductInfoServiceRunnable productInfoRunnable = new ProductInfoServiceRunnable(productId, productInfoService);
		Thread thread1 = new Thread(productInfoRunnable);
		thread1.start();
		ReviewServiceRunnable reviewInfoRunnable = new ReviewServiceRunnable(productId, reviewService);
		Thread thread2 = new Thread(reviewInfoRunnable);
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Review review = reviewInfoRunnable.getReview();
		ProductInfo productInfo=productInfoRunnable.getProductInfo();
		Product product = new Product(productId, productInfo, review);
		CommonUtil.stopTimer();
		CommonUtil.timeTaken();
		return product;
	}
	
	private static class ProductInfoServiceRunnable implements Runnable{

		private String productId;
		private ProductInfoService productInfoService;
		private ProductInfo productInfo;
		
		private ProductInfo getProductInfo() {
			return productInfo;
		}

		private ProductInfoServiceRunnable(String productId, ProductInfoService productInfoService) {
			this.productId = productId;
			this.productInfoService=productInfoService;
		}

		@Override
		public void run() {
			productInfo = productInfoService.retrieveProductInfo(productId);
		}
		
	}
	private static class ReviewServiceRunnable implements Runnable{

		private String productId;
		private ReviewService reviewService;
		private Review review;
		

		public Review getReview() {
			return review;
		}

		private ReviewServiceRunnable(String productId, ReviewService reviewService) {
			this.productId = productId;
			this.reviewService=reviewService;
		}

		@Override
		public void run() {
			review = reviewService.retrieveReview(productId);
		}
		
	}

}
