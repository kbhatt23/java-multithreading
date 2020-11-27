package com.learning.multithreading.studentlibrary;

import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.learning.multithreading.diningphilosopher.util.ThreadUtil;
import com.learning.multithreading.studentlibrary.bean.Book;
import com.learning.multithreading.studentlibrary.bean.Student;

public class StudentLibraryrunner {
	public static void main(String[] args) {

		List<Book> books = LibraryUtil.findAllBooks();
		List<Student> students = LibraryUtil.findAllStudents();
		ExecutorService service = Executors.newFixedThreadPool(LibraryUtil.STUDENT_SIZE);
		
		for(Student student: students) {
		Runnable task = () -> {
			while(true) {
				if(Thread.currentThread().isInterrupted()) {
					break;
				}
				int bookId = new Random().nextInt(LibraryUtil.BOOKS_SIZE);
				try {
					student.issueBook(books.get(bookId));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					break;
				}
				}
				
		};
		service.execute(task);
		}
		
		
		service.shutdown();
		
		ThreadUtil.sleep(60000);
		service.shutdownNow();
		
		for(Student stu:students) {
			System.out.println("Displaying books for student "+stu);
			for(Entry<Integer, Integer> entry : stu.getBookCount().entrySet()) {
				System.out.println("Book- "+entry.getKey()+" : "+entry.getValue());
			}
			System.out.println("=================");
		}
	}
}