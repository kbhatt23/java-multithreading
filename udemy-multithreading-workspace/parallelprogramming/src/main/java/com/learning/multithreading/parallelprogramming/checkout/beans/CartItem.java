package com.learning.multithreading.parallelprogramming.checkout.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class CartItem {

	private String cartItemId;
	
	private double totalPrice; 
	
	private int quantity;
	
	private String productId;
}
