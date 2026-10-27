package com.learning.principles;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CompositionOverInheritanceTest {

    @Test
    void eagleCanFlyOnly() {
        String desc = CompositionOverInheritance.eagle().describe();
        assertTrue(desc.contains("flying"));
        assertFalse(desc.contains("swimming"));
    }

    @Test
    void duckCanFlyAndSwim() {
        String desc = CompositionOverInheritance.duck().describe();
        assertTrue(desc.contains("flying"));
        assertTrue(desc.contains("swimming"));
    }

    @Test
    void penguinCanSwimOnly() {
        String desc = CompositionOverInheritance.penguin().describe();
        assertFalse(desc.contains("flying"));
        assertTrue(desc.contains("swimming"));
    }
}
