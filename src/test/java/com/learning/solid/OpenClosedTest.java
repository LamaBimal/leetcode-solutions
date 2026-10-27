package com.learning.solid;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OpenClosedTest {

    private final OpenClosed oc = new OpenClosed();

    @Test
    void circleArea() {
        assertEquals(Math.PI * 4, new OpenClosed.Circle(2).area(), 1e-9);
    }

    @Test
    void rectangleArea() {
        assertEquals(12.0, new OpenClosed.Rectangle(3, 4).area(), 1e-9);
    }

    @Test
    void triangleArea() {
        assertEquals(6.0, new OpenClosed.Triangle(4, 3).area(), 1e-9);
    }

    @Test
    void totalAreaSumsAllShapes() {
        OpenClosed.Shape[] shapes = {
            new OpenClosed.Rectangle(2, 3),
            new OpenClosed.Circle(1)
        };
        assertEquals(6 + Math.PI, oc.totalArea(shapes), 1e-9);
    }
}
