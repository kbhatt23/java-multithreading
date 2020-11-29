package com.learning.multithreading.parallelprogramming.products.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductOption {

	private int optionId;
	
	private String size;
	
	private String color;
	
	private double price;
}
