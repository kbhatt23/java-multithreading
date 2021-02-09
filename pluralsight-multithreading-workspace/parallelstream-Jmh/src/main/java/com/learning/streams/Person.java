package com.learning.streams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.omg.CORBA.PolicyError;

import com.learning.benchmarking.WrapperObjects;

public class Person {

	private int age;
	
	private String name;

	public Person(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}

	public static List<Person> findAll(){
		return 
				IntStream.rangeClosed(1, 10)
					.mapToObj(i -> new Person(i, "user-"+i))
					.collect(Collectors.toList());
	}
}
