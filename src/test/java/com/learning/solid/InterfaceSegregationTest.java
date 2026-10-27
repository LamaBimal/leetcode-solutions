package com.learning.solid;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InterfaceSegregationTest {

    @Test
    void humanWorksAndEats() {
        InterfaceSegregation.Human human = new InterfaceSegregation.Human();
        assertEquals("Human working", human.work());
        assertEquals("Human eating",  human.eat());
    }

    @Test
    void robotOnlyWorks() {
        InterfaceSegregation.Robot robot = new InterfaceSegregation.Robot();
        assertEquals("Robot working", robot.work());
        // Robot does not implement Eatable — compile-time guarantee
        assertFalse(robot instanceof InterfaceSegregation.Eatable);
    }
}
