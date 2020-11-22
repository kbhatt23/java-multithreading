package com.learning.multithreading.threadmethods;

import java.util.Scanner;

import com.learning.multithreading.util.ThreadUtil;

public class ClockSimulation {
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Welome to my alarm timer");
		boolean exit = false;
		while (!exit) {
			int option = printAlloptions();
			switch (option) {
			case 0:
				exit = true;
				System.out.println("Thanks for using my alarm timer");
				break;
			case 1:
				long sleepSecond = printInitailOption();
				ThreadUtil.sleep(sleepSecond);
			default:
				break;
			}
		}
	}

	public static long printInitailOption() {
		System.out.println("kindly enter seconds to set alarm for: ");
		long time = 0;
		while (true) {
			if (scanner.hasNextLong()) {
				time = scanner.nextLong();
				break;
			} else {
				scanner.nextLine();
				System.out.println("Please enter time in integer format");
			}
		}
		return time * 1000;
	}

	public static int printAlloptions() {
		System.out.println("Enter 0 to exit");
		System.out.println("Enter 1 to set alarm seconds");
		int option = 0;
		while (true) {
			if (scanner.hasNextInt()) {
				option = scanner.nextInt();
				break;
			} else {
				System.out.println("Please enter valid option");
				scanner.nextLine();
			}
		}
		return option;
	}
}
