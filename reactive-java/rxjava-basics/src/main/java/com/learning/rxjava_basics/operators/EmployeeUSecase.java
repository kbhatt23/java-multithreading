package com.learning.rxjava_basics.operators;

import java.util.Comparator;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class EmployeeUSecase {
public static void main(String[] args) {
	List<Employee> allEmployees = Employee.findAll();
	System.out.println("allEmployees : "+allEmployees);
	int goodEmpsToConsider = 4;
	int itemsToSkip = allEmployees.size()-goodEmpsToConsider; 
			
	Observable.fromIterable(allEmployees)
	.filter(item -> item.getRating() >=3)
		.sorted(Comparator.comparing(Employee::getRating).reversed())
		//.skip(itemsToSkip)//take top 4
		.take(4)
		.toList()
		.subscribe(goodEmployees -> System.out.println("good emplyees found "+goodEmployees));
	
}
}
