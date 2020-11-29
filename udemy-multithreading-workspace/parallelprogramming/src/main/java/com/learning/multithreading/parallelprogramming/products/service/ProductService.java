package com.learning.multithreading.parallelprogramming.products.service;

import com.learning.multithreading.parallelprogramming.products.beans.Product;
import com.learning.multithreading.parallelprogramming.products.beans.ProductInfo;
import com.learning.multithreading.parallelprogramming.products.beans.Review;
import com.learning.multithreading.parallelprogramming.util.CommonUtil;

public class ProductService  implements IProductService{

	private ProductInfoService productInfoService;
	private ReviewService reviewService;
	

	public ProductService(ProductInfoService productInfoService, ReviewService reviewService) {
		this.productInfoService = productInfoService;
		this.reviewService = reviewService;
	}

	public Product retrieveProductDetails(String productId) {
		CommonUtil.resetTimer();
		CommonUtil.startTimer();
		ProductInfo productInfo = productInfoService.retrieveProductInfo(productId);
		Review review = reviewService.retrieveReview(productId);
		Product product = new Product(productId, productInfo, review);
		CommonUtil.stopTimer();
		CommonUtil.timeTaken();
		return product;
	}

}
