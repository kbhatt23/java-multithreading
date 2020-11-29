package com.learning.multithreading.parallelprogramming.checkout.service;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Stream;

import com.learning.multithreading.parallelprogramming.checkout.beans.CartItem;
import com.learning.multithreading.parallelprogramming.checkout.beans.CartSession;
import com.learning.multithreading.parallelprogramming.util.CommonUtil;

public class CartValidationService {

	
	public void validateCartPrice(CartSession cartSession,boolean isParallel, boolean isInvalidCart) {
		CommonUtil.resetTimer();
		CommonUtil.startTimer();
		
		Stream<CartItem> cartItemsStream = isInvalidCart ? cartSession.findInvalidSessionCart().getCartItems().stream()
				: cartSession.findValidSessionCart().getCartItems().stream();
		if(isParallel)
			cartItemsStream.parallel();
		
		boolean isInvalid = cartItemsStream
							.map(SynchronyValidationService:: validateSynchronyPrice)
							.anyMatch(i -> !i);
		if(isInvalid) {
			CommonUtil.stopTimer();
			CommonUtil.timeTaken();
			throw new RuntimeException("Cart Session is invalid");
			}
		
		CommonUtil.stopTimer();
		CommonUtil.timeTaken();
	}
	public void checkout(CartSession cartSession,boolean isParallel, boolean isInvalidCart) {
		validateCartPrice(cartSession, isParallel, isInvalidCart);
		
		//if valid calcualte total price
		double totalCost = calculatePrice(cartSession);
		
		System.out.println("Kindly pay "+totalCost);
	}
	private double calculatePrice(CartSession cartSession) {
		double totalCost = cartSession.findValidSessionCart().getCartItems().stream()
												.map(CartItem::getTotalPrice)
												.mapToDouble(i -> i)
												.sum();
		return totalCost;
	}
	
	public static void main(String[] args) {
		CartValidationService obj = new CartValidationService();
		obj.checkout(new CartSession(),true,false);
	}
	
	

	public void validateBulkCartPrice(CartSession cartSession,boolean isParallel,boolean isBulkPool) {
		CommonUtil.resetTimer();
		CommonUtil.startTimer();
		
		int sizeOfCart = 100;
		Stream<CartItem> cartItemsStream = cartSession.findBulkSessionCart(sizeOfCart).getCartItems().stream();
		if(isParallel)
			cartItemsStream.parallel();
		
		//the validate price method is i/o intensive call meaning te procesor is free for a lot of time as thread is sleeping and in bbocked state waiting ofr web service reply
		//hen ce we can create more threads than the cores,thats why creating custom threadpool for better perforamnce
		boolean isInvalid;
		if(isBulkPool) {
			ForkJoinPool pool = new ForkJoinPool(sizeOfCart);
			ForkJoinTask<Boolean> bulkFuture = pool.submit(() -> cartItemsStream
					.map(SynchronyValidationService:: validateSynchronyPrice)
					.anyMatch(i -> !i));
			
			isInvalid=bulkFuture.join();
		}else {
			 isInvalid = cartItemsStream
					.map(SynchronyValidationService:: validateSynchronyPrice)
					.anyMatch(i -> !i);
		}
		
		if(isInvalid) {
			CommonUtil.stopTimer();
			CommonUtil.timeTaken();
			throw new RuntimeException("Cart Session is invalid");
			}
		
		CommonUtil.stopTimer();
		CommonUtil.timeTaken();
	}
}
