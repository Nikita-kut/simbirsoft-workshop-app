package com.nikita.kut.android.simbirsoft_workshop;

public class Rectangle implements Shape {

    private final double length;
    private final double width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public double perimeter() {
        return length * 2 + width * 2;
    }

    @Override
    public double area() {
        return width * length;
    }
}
