package com.learning.multithreading.parallelprogramming.checkout.service;

import com.learning.multithreading.parallelprogramming.checkout.beans.CartItem;
import com.learning.multithreading.parallelprogramming.util.CommonUtil;

public class SynchronyValidationService {

	public static boolean validateSynchronyPrice(CartItem cartItem) {
		System.out.println("Starting validation for cartItem "+cartItem.getCartItemId()+" with product id "+cartItem.getProductId());
		
		//populate request bean and call service
		CommonUtil.sleep(500);
		boolean isValid=false;
		switch (cartItem.getProductId()) {
		case "prod-1":
			isValid= true;
			break;
		case "prod-2":
			isValid= true;
			break;
		case "prod-3":
			isValid= true;
			break;
		case "prod-4":
			isValid= true;
			break;
		default:
			isValid= true;
		}
		System.out.println("validation result cartItem "+cartItem.getCartItemId()+" with product id "+cartItem.getProductId() + " is: "+isValid);
		return isValid;
	}
}
