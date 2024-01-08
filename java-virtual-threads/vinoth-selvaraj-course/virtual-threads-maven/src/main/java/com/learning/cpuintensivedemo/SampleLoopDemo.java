package com.learning.cpuintensivedemo;

public class SampleLoopDemo {
    public static void main(String[] args) {
        String response = findResponse();
        System.out.println(response);
    }

    private static String findResponse() {
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0 ; i < 4 ; i++){
            try{
                stringBuilder.append("data-"+i);
                if(i == 2)
                    return stringBuilder.toString();
            }catch (Exception e){

            }finally {
                System.out.println("finally called");
                stringBuilder.append("-finally");
            }
        }

        return stringBuilder.toString();
    }
}
