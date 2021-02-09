package com.learning.parallestream;

import java.util.function.Function;

public class LazyBean {

	private int id;
	
	private String name;

	public int getId() {
		return id;
	}

	public LazyBean addId(int id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public LazyBean addName(String name) {
		this.name = name;
		return this;
	}
	
	public static void placeOrder(Function<LazyBean,LazyBean> lazyMapper) {
		LazyBean lazyBean = new LazyBean();
		
		lazyMapper.apply(lazyBean);
		System.out.println("checking out lazy bean "+lazyBean);
	}
	@Override
	public String toString() {
		return "LazyBean [id=" + id + ", name=" + name + "]";
	}
	
	
}
