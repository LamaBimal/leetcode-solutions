package com.learning.solid;

/**
 * I - Interface Segregation Principle (ISP)
 *
 * Clients should not be forced to depend on interfaces they don't use.
 *
 * BAD: One fat Worker interface with work(), eat(), and sleep() forces
 *      a Robot to implement eat() and sleep() which don't apply.
 * GOOD: Split into focused interfaces so each class only implements what it needs.
 */
public class InterfaceSegregation {

    public interface Workable {
        String work();
    }

    public interface Eatable {
        String eat();
    }

    // Human needs both
    public static class Human implements Workable, Eatable {
        @Override
        public String work() { return "Human working"; }

        @Override
        public String eat() { return "Human eating"; }
    }

    // Robot only needs Workable — not forced to implement eat()
    public static class Robot implements Workable {
        @Override
        public String work() { return "Robot working"; }
    }
}
