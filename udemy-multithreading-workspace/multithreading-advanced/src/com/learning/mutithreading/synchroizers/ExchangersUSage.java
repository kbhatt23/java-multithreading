package com.learning.mutithreading.synchroizers;

import java.util.concurrent.Exchanger;

//it is a two way communcationq queue
//if one thread sends message to excahnger, than it blocks and waits
//untill another thread sends message, so that each of thread sends one message and recive one by other

public class ExchangersUSage {
public static void main(String[] args) {
	Exchanger<String> exchanger = new Exchanger<String>();
	
	Runnable t1 =() ->{
		String message = "jai shree ram";
		System.out.println(Thread.currentThread().getName()+" sending message "+message);
		try {
			String response = exchanger.exchange(message);
			
			System.out.println(Thread.currentThread().getName()+" recieved message "+response);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	};
	
	Runnable t2 =() ->{
		String message = "jai radhe krishna";
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+" sending message "+message);
		try {
			String response = exchanger.exchange(message);
			
			System.out.println(Thread.currentThread().getName()+" recieved message "+response);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	};
	
	new Thread(t1).start();
	new Thread(t2).start();
}
}
