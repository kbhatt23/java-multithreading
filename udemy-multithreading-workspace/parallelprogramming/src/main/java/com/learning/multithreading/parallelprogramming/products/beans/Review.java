package com.learning.multithreading.parallelprogramming.products.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

	private int numOfReviews;
	
	private double overallRating;
}
