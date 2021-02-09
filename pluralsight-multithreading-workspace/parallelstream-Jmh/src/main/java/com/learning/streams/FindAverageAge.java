package com.learning.streams;

import java.util.List;

public class FindAverageAge {

	public static void main(String[] args) {
		List<Person> persons = Person.findAll();
		int thresholdAge= 9;
		usingForLoop(persons,thresholdAge);
		usingStream(persons, thresholdAge);
	}

	private static void usingStream(List<Person> persons, int thresholdAge) {

		double average = 
				persons.stream()
					.filter(p -> p.getAge() > thresholdAge)
					.mapToInt(Person::getAge)
					.average()
					.orElse(0)
					;
		System.out.println("stream average is "+average);
	}
	
	private static void usingForLoop(List<Person> persons, int thresholdAge) {

		int sum = 0 ;
		int count=0;
		
		for(Person person:persons) {
			int age = person.getAge();
			if(age > thresholdAge) {
				sum+=age;
				count++;
			}
		}
		double average = 0;
		if(count > 0) {
			average = sum/count;
		}
		System.out.println("for loop average is "+average);
	}
	
}
