package com.example.sudokuwebapp.api.model;
public class Number {
    /* Attributes */
    private int value;
    private boolean isFixed;

    /* Builder */
    public Number(int value, boolean isFixed) {
        this.value = value;
        this.isFixed = isFixed;
    }

    /* Methods */
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isFixed() {
        return isFixed;
    }

}

