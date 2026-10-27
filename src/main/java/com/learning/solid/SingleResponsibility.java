package com.learning.solid;

/**
 * S - Single Responsibility Principle (SRP)
 *
 * A class should have only one reason to change.
 *
 * BAD: A single class that both parses an order AND sends an email notification.
 * GOOD: Split into Order (data) and OrderNotifier (notification concern).
 */
public class SingleResponsibility {

    // Handles only order data
    public static class Order {
        private final int id;
        private final double total;

        public Order(int id, double total) {
            this.id = id;
            this.total = total;
        }

        public int getId() { return id; }
        public double getTotal() { return total; }
    }

    // Handles only notification logic — separate reason to change
    public static class OrderNotifier {
        public String notify(Order order) {
            return "Order #" + order.getId() + " confirmed. Total: $" + order.getTotal();
        }
    }
}
