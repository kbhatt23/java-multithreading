package com.learning.scopedvalues;

public class StringHolder{

    private String value;

    public StringHolder(String holder) {
        this.value = holder;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "StringHolder-" + hashCode()+ " :{" +
                "value='" + value + '\'' +
                '}';
    }
}