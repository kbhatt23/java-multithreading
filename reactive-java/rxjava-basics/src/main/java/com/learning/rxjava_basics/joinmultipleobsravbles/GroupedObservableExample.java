package com.learning.rxjava_basics.joinmultipleobsravbles;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.learning.rxjava_basics.operators.Employee;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

public class GroupedObservableExample {
public static void main(String[] args) {
	Employee e1=  new Employee(1, "krishna", 5);
	Employee e2=  new Employee(2, "mohan", 5);
	Employee e3=  new Employee(3, "lambda", 3);
	Employee e4=  new Employee(4, "colection", 4);
	Employee e5=  new Employee(5, "clone", 3);
	Employee e6=  new Employee(6, "javascript", 2);
	Employee e7=  new Employee(7, "serialization", 3);
	
	Stream.of(e1,e2,e3,e4,e5,e6,e7).collect(Collectors.groupingBy(Employee::getRating))
		.forEach((rating,employyes) -> System.out.println(rating+ " -> "+employyes));
	
	System.out.println("=================");
	@NonNull
	Observable<Employee> employeesData = Observable.fromIterable(Arrays.asList(e1,e2,e3,e4,e5,e6,e7));
	
	//group by rating
	employeesData.groupBy(Employee::getRating) // returns Obseravle<observable> , hence using flatmap
//		.flatMapSingle(e -> e.toMultimap(k -> e.getKey(), emp -> emp.getName()))
//			.subscribe(group -> System.out.println(group))
		.flatMapSingle(e -> e.toList()) // however toList return Single, thats why using flatMapsingle
		.subscribe(group -> System.out.println(group))
	;
	
}
}
