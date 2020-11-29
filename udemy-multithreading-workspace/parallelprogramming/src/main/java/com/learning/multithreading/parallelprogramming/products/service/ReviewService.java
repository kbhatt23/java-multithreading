package com.learning.multithreading.parallelprogramming.products.service;

import com.learning.multithreading.parallelprogramming.products.beans.Review;
import com.learning.multithreading.parallelprogramming.util.CommonUtil;

public class ReviewService {

	protected Review retrieveReview(String productId) {
		CommonUtil.sleep(1000);
		return new Review(1008, 4.1);
	}


}
