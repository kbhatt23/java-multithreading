package com.learning.scopedvalues;

public class SingleThreadedScopedValueDemo {
    private static final ScopedValue<StringHolder> scope = ScopedValue.newInstance();
    public static void main(String[] args) throws Exception {

        //will be false as no bound set yet
        System.out.println("pre: scope.bound: "+scope.isBound());

        //will give exception as isBound will be false
//        if(scope.isBound())
//            System.out.println("pre: scope.value: "+scope.get());
        StringHolder stringHolder = new StringHolder("jai shree sita rama lakshman hanuman");

        boolean result  = ScopedValue.callWhere(scope,stringHolder, SingleThreadedScopedValueDemo :: handleUser);
        System.out.println(result);

        //should still be false
        //thread is allowed but this is bound to handleUser method only
        System.out.println("post: scope.bound: "+scope.isBound());
    }

    private static boolean handleUser() {

        if(scope.isBound()){
            System.out.println("handleUser: "+scope.get());
        }

        return scope.isBound();
    }
}
