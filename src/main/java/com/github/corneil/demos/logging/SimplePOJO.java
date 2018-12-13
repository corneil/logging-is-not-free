package com.github.corneil.demos.logging;

import java.util.Date;

public class SimplePOJO {
    private String someString;
    private int someValue;
    private double someDouble;
    private Date someDate;

    public SimplePOJO(String someString, int someValue, double someDouble, Date someDate) {
        this.someString = someString;
        this.someValue = someValue;
        this.someDouble = someDouble;
        this.someDate = someDate;
    }

    public String getSomeString() {
        return someString;
    }

    public void setSomeString(String someString) {
        this.someString = someString;
    }

    public int getSomeValue() {
        return someValue;
    }

    public void setSomeValue(int someValue) {
        this.someValue = someValue;
    }

    public double getSomeDouble() {
        return someDouble;
    }

    public void setSomeDouble(double someDouble) {
        this.someDouble = someDouble;
    }

    public Date getSomeDate() {
        return someDate;
    }

    public void setSomeDate(Date someDate) {
        this.someDate = someDate;
    }

    @Override
    public String toString() {
        return "SimplePOJO{" +
                "someString='" + someString + '\'' +
                ", someValue=" + someValue +
                ", someDouble=" + someDouble +
                ", someDate=" + someDate +
                '}';
    }
}
