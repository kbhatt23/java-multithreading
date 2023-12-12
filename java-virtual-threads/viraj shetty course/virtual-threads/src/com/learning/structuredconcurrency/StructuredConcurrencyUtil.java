package com.learning.structuredconcurrency;

import java.util.concurrent.StructuredTaskScope;

public class StructuredConcurrencyUtil {

    public static void printResult(StructuredTaskScope.Subtask<?> subtask){
        if(subtask.state().equals(StructuredTaskScope.Subtask.State.SUCCESS)){
            System.out.println("printResult: success response received: "+subtask.get());
        } else if (subtask.state().equals(StructuredTaskScope.Subtask.State.FAILED)) {
            System.err.println("printResult: error occurred: "+subtask.exception());
        }
    }
}
