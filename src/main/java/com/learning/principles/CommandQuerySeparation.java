package com.learning.principles;

/**
 * Command-Query Separation (CQS)
 *
 * A method should either DO something (command) or RETURN something (query) — not both.
 *
 * BAD: pop() removes an item AND returns it — side effect mixed with query.
 * GOOD: peek() queries the top; remove() is the command. Each does one thing.
 */
public class CommandQuerySeparation {

    public static class Stack<T> {
        private final java.util.Deque<T> data = new java.util.ArrayDeque<>();

        // Command — changes state, returns nothing
        public void push(T item) {
            data.push(item);
        }

        // Command — changes state, returns nothing
        public void remove() {
            if (data.isEmpty()) throw new java.util.NoSuchElementException("Stack is empty");
            data.pop();
        }

        // Query — reads state, no side effects
        public T peek() {
            if (data.isEmpty()) throw new java.util.NoSuchElementException("Stack is empty");
            return data.peek();
        }

        // Query — reads state, no side effects
        public boolean isEmpty() { return data.isEmpty(); }

        // Query
        public int size() { return data.size(); }
    }
}
