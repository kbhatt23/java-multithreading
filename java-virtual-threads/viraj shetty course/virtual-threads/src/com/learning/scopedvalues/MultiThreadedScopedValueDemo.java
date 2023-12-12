package com.learning.scopedvalues;

import java.util.concurrent.StructuredTaskScope;

public class MultiThreadedScopedValueDemo {
    private static final ScopedValue<StringHolder> scope1 = ScopedValue.newInstance();


    public static void main(String[] args) {
        System.out.println("main.pre isBound: "+scope1.isBound());

        ScopedValue.runWhere(scope1, new StringHolder("first value") , MultiThreadedScopedValueDemo:: runScope);


        System.out.println("main.post isBound: "+scope1.isBound());
    }

    private static void runScope()  {
        System.out.println("main.runScope isBound: "+scope1.isBound());

        System.out.println("main.runScope value: "+scope1.get());

        try(StructuredTaskScope<StringHolder> objectStructuredTaskScope = new StructuredTaskScope<>()){

            objectStructuredTaskScope.fork( () ->{
                System.out.println("fork.runScope isBound: "+scope1.isBound());

                System.out.println("fork.runScope value: "+scope1.get());


                return scope1.get();
            });

            try {
                objectStructuredTaskScope.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private static void runSecondScope() {
        //if done using another thread wont work
       // Thread.ofPlatform().start( () -> {
            System.out.println("main.runSecondScope isBound: " + scope1.isBound());

            System.out.println("main.runSecondScope value: " + scope1.get());
        //});
    }
}
