package com.learning.multithreading.parallelprogramming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World! says sample class "+new SampleClass(1, "sita-ram", "jai sita ram"));
    }
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class SampleClass{
	private int id;
	private String name;
	private String description;
}
