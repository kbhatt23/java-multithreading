package com.learning.rxjava_basics.operators;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Employee {

	private int id;
	private String name;
	private double rating;
	private static final Random random =new Random();
	public Employee(int id, String name, double rating) {
		
		this.id = id;
		this.name = name;
		this.rating = rating;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", rating=" + rating + "]";
	}
	
	
	public static List<Employee> findAll(){
		return IntStream.rangeClosed(1, 10)
				.mapToObj(id -> new Employee(id, "user-"+id,2 + (5 - 2) * random.nextDouble() ) )
				.collect(Collectors.toList())
				;
	}
}
