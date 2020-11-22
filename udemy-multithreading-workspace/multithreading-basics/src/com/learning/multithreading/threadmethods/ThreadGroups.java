package com.learning.multithreading.threadmethods;

public class ThreadGroups {
public static void main(String[] args) {
	//finding thread group of any thread
	
	ThreadGroup group = Thread.currentThread().getThreadGroup();
	System.out.println(group.getName());
	ThreadGroup parentGroup = group.getParent();
	printParentName(parentGroup);
	
	printParentName(parentGroup.getParent());
	
}

private static void printParentName(ThreadGroup parentGroup) {
	String parentName = parentGroup != null ? parentGroup.getName(): "Empty group";
	System.out.println(parentName);
}
}
