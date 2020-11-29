package com.learning.multithreading.parallelprogramming.products.service;

import java.util.Arrays;
import java.util.List;

import com.learning.multithreading.parallelprogramming.products.beans.ProductInfo;
import com.learning.multithreading.parallelprogramming.products.beans.ProductOption;
import com.learning.multithreading.parallelprogramming.util.CommonUtil;

public class ProductInfoService {
	protected ProductInfo retrieveProductInfo(String productId) {
		CommonUtil.sleep(1000);
		List<ProductOption> propductOptions = Arrays.asList(ProductOption.builder().optionId(1).color("Red").size("64GB").price(699.99).build(),
				ProductOption.builder().optionId(2).color("Black").size("128GB").price(749.99).build()
				,ProductOption.builder().optionId(3).color("Black").size("64GB").price(699.99).build(),
				ProductOption.builder().optionId(4).color("Red").size("128GB").price(749.99).build())
		;
		
		return new ProductInfo(propductOptions);
	}
}
