package com.learning.solid;

/**
 * L - Liskov Substitution Principle (LSP)
 *
 * Subtypes must be substitutable for their base types without altering correctness.
 *
 * BAD: Square extends Rectangle but overriding setWidth/setHeight breaks Rectangle's contract.
 * GOOD: Both Square and Rectangle implement a common Shape interface independently.
 */
public class LiskovSubstitution {

    public interface Shape {
        double area();
    }

    public static class Rectangle implements Shape {
        protected double width, height;

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public double area() { return width * height; }
    }

    // Square does NOT extend Rectangle — avoids the classic LSP violation
    public static class Square implements Shape {
        private final double side;

        public Square(double side) { this.side = side; }

        @Override
        public double area() { return side * side; }
    }

    // Works correctly with any Shape — LSP satisfied
    public double computeArea(Shape shape) {
        return shape.area();
    }
}
