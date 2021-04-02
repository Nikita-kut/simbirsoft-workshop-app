package com.nikita.kut.android.simbirsoft_workshop;

public class Circle implements Shape {

    private final double diameter;

    public Circle(double diameter) {
        this.diameter = diameter;
    }

    public double getDiameter() {
        return diameter;
    }

    @Override
    public double perimeter() {
        return Math.PI * diameter;
    }

    @Override
    public double area() {
        double radius = diameter / 2;
        return Math.PI * (radius * radius);
    }
}
