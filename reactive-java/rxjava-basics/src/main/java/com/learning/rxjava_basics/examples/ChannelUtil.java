package com.learning.rxjava_basics.examples;

import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;

public class ChannelUtil {
	public static final Consumer<String> dataChannel = data -> System.out.println("recieved data " + data);
	public static final Consumer<Throwable> errorChannel = error -> System.out.println("error occurred " + error);
	public static final Action completeChannel = () -> System.out.println("all task completed sucesfully");
}
