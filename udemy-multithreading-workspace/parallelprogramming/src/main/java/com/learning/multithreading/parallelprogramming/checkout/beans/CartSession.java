package com.learning.multithreading.parallelprogramming.checkout.beans;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CartSession {

	public Cart findValidSessionCart() {
		CartItem cartItem1 = new CartItem("Cart-Item-1", 99.99, 1, "prod-1");
		CartItem cartItem2 = new CartItem("Cart-Item-2", 199.99, 2, "prod-2");
		CartItem cartItem3 = new CartItem("Cart-Item-3", 219.98, 1, "prod-3");
		CartItem cartItem4 = new CartItem("Cart-Item-4", 603, 3, "prod-4");
		List<CartItem> cartItems = Arrays.asList(
				cartItem1,cartItem2,cartItem3,cartItem4
				);
		return new Cart("Cart-1", cartItems);
	}
	
	public Cart findInvalidSessionCart() {
		CartItem cartItem1 = new CartItem("Cart-Item-1", 99.99, 1, "prod-1");
		CartItem cartItem2 = new CartItem("Cart-Item-2", 199.99, 2, "prod-2");
		CartItem cartItem3 = new CartItem("Cart-Item-3", 219.98, 1, "prod-3");
		CartItem cartItem4 = new CartItem("Cart-Item-4", 603, 3, "prod-9");
		List<CartItem> cartItems = Arrays.asList(
				cartItem1,cartItem2,cartItem3,cartItem4
				);
		return new Cart("Cart-1", cartItems);
	}
	
	public Cart findBulkSessionCart(int size) {
		List<CartItem> cartItems = IntStream.rangeClosed(1, size)
				.mapToObj(i -> new CartItem("Cart-Item-"+i, 99.99+i, 1, "prod-"+i))
				.collect(Collectors.toList());
		return new Cart("Cart-1", cartItems);
	}
}
