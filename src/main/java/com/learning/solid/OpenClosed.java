package com.learning.solid;

/**
 * O - Open/Closed Principle (OCP)
 *
 * Classes should be open for extension, but closed for modification.
 *
 * BAD: Adding a new shape requires editing existing switch/if logic.
 * GOOD: Each shape implements an interface; new shapes extend without touching existing code.
 */
public class OpenClosed {

    public interface Shape {
        double area();
    }

    public static class Circle implements Shape {
        private final double radius;
        public Circle(double radius) { this.radius = radius; }

        @Override
        public double area() { return Math.PI * radius * radius; }
    }

    public static class Rectangle implements Shape {
        private final double width, height;
        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public double area() { return width * height; }
    }

    // Adding a Triangle requires NO changes to existing classes
    public static class Triangle implements Shape {
        private final double base, height;
        public Triangle(double base, double height) {
            this.base = base;
            this.height = height;
        }

        @Override
        public double area() { return 0.5 * base * height; }
    }

    public double totalArea(Shape[] shapes) {
        double total = 0;
        for (Shape s : shapes) total += s.area();
        return total;
    }
}
