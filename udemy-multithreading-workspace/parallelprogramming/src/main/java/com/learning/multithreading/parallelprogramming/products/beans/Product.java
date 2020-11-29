package com.learning.multithreading.parallelprogramming.products.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

	private String productId;
	
	private ProductInfo productInfo;
	
	private Review review;
}
