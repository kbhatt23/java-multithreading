package com.learning.multithreading.parallelprogramming.checkout.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.concurrent.ForkJoinPool;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.learning.multithreading.parallelprogramming.checkout.beans.CartSession;

class CartValidationServiceTest {
	CartValidationService obj = new CartValidationService();
	

	@ParameterizedTest
	@ValueSource(booleans = true)
	void test(boolean isParallel) {

		obj.validateCartPrice(new CartSession(), isParallel, false);
	}

	@ParameterizedTest
	@ValueSource(booleans = true)
	void test1(boolean isParallel) {
		Exception exception = assertThrows(RuntimeException.class, () -> {
			obj.validateCartPrice(new CartSession(), isParallel, true);
		});

		String expectedMessage = "Cart Session is invalid";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);


	}

	//for i/o intensive we are creating custom forkjoinpool way more than the size of cores
	@ParameterizedTest
	@ValueSource(booleans = true)
	void validateBulkCartPriceParallel(boolean isParallel) {
		//huge io intensive tasks are there and hence we cna create more threads than size of core
		obj.validateBulkCartPrice(new CartSession(), isParallel,true);

	}

}
