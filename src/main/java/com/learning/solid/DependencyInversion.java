package com.learning.solid;

/**
 * D - Dependency Inversion Principle (DIP)
 *
 * High-level modules should not depend on low-level modules.
 * Both should depend on abstractions.
 *
 * BAD: OrderService directly instantiates MySQLDatabase (tight coupling).
 * GOOD: OrderService depends on a Database interface; the concrete impl is injected.
 */
public class DependencyInversion {

    // Abstraction
    public interface Database {
        void save(String data);
        String load(String id);
    }

    // Low-level module
    public static class MySQLDatabase implements Database {
        @Override
        public void save(String data) { /* persist to MySQL */ }

        @Override
        public String load(String id) { return "data-from-mysql-" + id; }
    }

    // Another low-level module — swappable without touching OrderService
    public static class InMemoryDatabase implements Database {
        private final java.util.Map<String, String> store = new java.util.HashMap<>();

        @Override
        public void save(String data) { store.put(data, data); }

        @Override
        public String load(String id) { return store.getOrDefault(id, null); }
    }

    // High-level module depends on the abstraction, not a concrete class
    public static class OrderService {
        private final Database database;

        public OrderService(Database database) {
            this.database = database; // injected
        }

        public void placeOrder(String order) {
            database.save(order);
        }

        public String getOrder(String id) {
            return database.load(id);
        }
    }
}
