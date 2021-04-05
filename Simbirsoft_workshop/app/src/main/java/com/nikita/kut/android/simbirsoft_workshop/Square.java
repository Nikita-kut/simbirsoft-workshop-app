package com.nikita.kut.android.simbirsoft_workshop;

public class Square implements Shape {

    private final double side;

    public Square(double side) {
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    @Override
    public double perimeter() {
        return side * 4;
    }

    @Override
    public double area() {
        return side * side;
    }
}
