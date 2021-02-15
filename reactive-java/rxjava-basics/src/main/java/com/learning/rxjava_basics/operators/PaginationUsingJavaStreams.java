package com.learning.rxjava_basics.operators;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PaginationUsingJavaStreams {
public static void main(String[] args) {
	List<Integer> items = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11);
	
	//starts from 0
	int pageNumber = 5;
	int sizePerPage = 2;
	List<Integer> pageData = paginateData(items,pageNumber,sizePerPage);
	System.out.println(pageData);
	
}

private static List<Integer> paginateData(List<Integer> items, int pageNumber, int sizePerPage) {
	int skipCount = pageNumber * sizePerPage;
	
	return items.stream()
				.skip(skipCount)
				.limit(sizePerPage)
				.collect(Collectors.toList());
}
}
