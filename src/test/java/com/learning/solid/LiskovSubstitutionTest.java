package com.learning.solid;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LiskovSubstitutionTest {

    private final LiskovSubstitution ls = new LiskovSubstitution();

    @Test
    void rectangleArea() {
        assertEquals(20.0, ls.computeArea(new LiskovSubstitution.Rectangle(4, 5)), 1e-9);
    }

    @Test
    void squareArea() {
        assertEquals(9.0, ls.computeArea(new LiskovSubstitution.Square(3)), 1e-9);
    }

    @Test
    void bothSubstitutableForShape() {
        LiskovSubstitution.Shape rect = new LiskovSubstitution.Rectangle(2, 6);
        LiskovSubstitution.Shape sq   = new LiskovSubstitution.Square(4);
        assertEquals(12.0, ls.computeArea(rect), 1e-9);
        assertEquals(16.0, ls.computeArea(sq),   1e-9);
    }
}
