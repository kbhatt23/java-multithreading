package com.learning.multithreading.parallelprogramming.checkout.beans;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cart {

	private String cartId;
	
	private List<CartItem> cartItems;
}
