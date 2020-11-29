package com.learning.multithreading.parallelprogramming.products.service;

import com.learning.multithreading.parallelprogramming.products.beans.Product;

public interface IProductService {

	Product retrieveProductDetails(String productId);

}
