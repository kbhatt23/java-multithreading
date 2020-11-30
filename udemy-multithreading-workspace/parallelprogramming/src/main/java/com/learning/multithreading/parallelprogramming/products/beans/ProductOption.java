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
	//inventory can be fetched from inventory serviuce based on the above option id
	//so invenroty servuce cna be called only after product service is called and its options are fectehd
	//for each options the invenotry will be different
	
	private Inventory inventory;
}
