package com.learning.multithreading.concurrentutils;

import java.util.concurrent.Exchanger;

import com.learning.multithreading.util.ThreadUtil;

public class ExcahngeUsage {
public static void main(String[] args) {
	Exchanger<String> exchanger = new Exchanger<String>();
	
	new Thread(new ExchangeTask<>(exchanger, "jai shree ram"), "thread-one").start();
	ThreadUtil.sleep(1000);
	new Thread(new ExchangeTask<>(exchanger, "jai radhe krishna"), "thread-two").start();
	
	ThreadUtil.sleep(1000);
	Exchanger<Integer> exchangerInteger = new Exchanger<>();
	new Thread(new ExchangeTask<>(exchangerInteger, 1), "thread-one").start();
	ThreadUtil.sleep(1000);
	new Thread(new ExchangeTask<>(exchangerInteger, 2), "thread-two").start();
	
}
private static class ExchangeTask<T> implements Runnable{

	private Exchanger<T> exchanger;
	private T message;
	private ExchangeTask(Exchanger<T> exchanger,T message) {
		this.exchanger=exchanger;
		this.message=message;
	}
	@Override
	public void run() {
		System.out.println(String.format("Message %s send by thread: %s", message,Thread.currentThread().getName()));
		try {
			//exchange method is blocking until another thread also calls the eschange method
			//it is a twoway bidirectional excahnge queue
			T reciveingMessage = exchanger.exchange(message);
			System.out.println(String.format("Message %s recieved by thread: %s", reciveingMessage,Thread.currentThread().getName()));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
}
