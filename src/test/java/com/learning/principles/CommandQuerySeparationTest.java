package com.learning.principles;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommandQuerySeparationTest {

    @Test
    void pushAndPeekDoNotMutateOnPeek() {
        CommandQuerySeparation.Stack<Integer> stack = new CommandQuerySeparation.Stack<>();
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.peek()); // query — size unchanged
        assertEquals(2, stack.size());
    }

    @Test
    void removeReducesSize() {
        CommandQuerySeparation.Stack<String> stack = new CommandQuerySeparation.Stack<>();
        stack.push("a");
        stack.push("b");
        stack.remove(); // command — no return value
        assertEquals(1, stack.size());
        assertEquals("a", stack.peek());
    }

    @Test
    void peekOnEmptyStackThrows() {
        CommandQuerySeparation.Stack<Integer> stack = new CommandQuerySeparation.Stack<>();
        assertThrows(java.util.NoSuchElementException.class, stack::peek);
    }
}
