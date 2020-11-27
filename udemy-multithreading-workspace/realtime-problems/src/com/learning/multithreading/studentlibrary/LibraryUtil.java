package com.learning.multithreading.studentlibrary;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.learning.multithreading.studentlibrary.bean.Book;
import com.learning.multithreading.studentlibrary.bean.Student;

public class LibraryUtil {
	public static final int BOOKS_SIZE = 11;
	public static final int GAME_SIMULATION_TIME_SECONDS = 60;	
	public static final int STUDENT_SIZE = 4;
	public static List<Book> findAllBooks(){
		return IntStream.rangeClosed(1, BOOKS_SIZE)
				.mapToObj(id -> new Book(id, new ReentrantLock(true)))
				.collect(Collectors.toList());
	}
	public static List<Student> findAllStudents(){
		return IntStream.rangeClosed(1, STUDENT_SIZE)
				.mapToObj(id -> new Student(id))
				.collect(Collectors.toList());
	}
	
}
