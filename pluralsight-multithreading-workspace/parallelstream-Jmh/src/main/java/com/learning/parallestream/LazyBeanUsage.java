package com.learning.parallestream;

import java.util.function.Function;

public class LazyBeanUsage {
	public static void main(String[] args) {

		//pipeline
//		Function<LazyBean, LazyBean> mapper = lazyBean ->{
//			lazyBean.addId(123);
//			lazyBean.addName("sample item");
//			return lazyBean;
//		};
		Function<LazyBean, LazyBean> mapper = lazyBean -> 
						lazyBean.addId(123).addName("sample item");
		
		LazyBean.placeOrder(mapper);
	}
}
