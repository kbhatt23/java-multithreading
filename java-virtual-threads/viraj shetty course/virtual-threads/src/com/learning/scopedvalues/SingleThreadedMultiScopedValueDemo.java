package com.learning.scopedvalues;

public class SingleThreadedMultiScopedValueDemo {
    private static final ScopedValue<StringHolder> scope1 = ScopedValue.newInstance();


    public static void main(String[] args) {
        System.out.println("main.pre isBound: "+scope1.isBound());

        ScopedValue.runWhere(scope1, new StringHolder("first value") , SingleThreadedMultiScopedValueDemo :: runScope);


        System.out.println("main.post isBound: "+scope1.isBound());
    }

    private static void runScope() {
        System.out.println("main.runScope isBound: "+scope1.isBound());

        System.out.println("main.runScope value: "+scope1.get());

        ScopedValue.runWhere(scope1, new StringHolder("second value") , SingleThreadedMultiScopedValueDemo :: runSecondScope);
    }

    private static void runSecondScope() {
        //if done using another thread wont work
       // Thread.ofPlatform().start( () -> {
            System.out.println("main.runSecondScope isBound: " + scope1.isBound());

            System.out.println("main.runSecondScope value: " + scope1.get());
        //});
    }
}
