package com.learning.principles;

/**
 * Law of Demeter (LoD) - Principle of Least Knowledge
 *
 * An object should only talk to its immediate collaborators.
 * Avoid chaining through objects you don't directly own.
 *
 * BAD: order.getCustomer().getAddress().getCity()  — reaching through the graph.
 * GOOD: order exposes getCity() directly, hiding the internal structure.
 */
public class LawOfDemeter {

    public static class Address {
        private final String city;
        public Address(String city) { this.city = city; }
        public String getCity() { return city; }
    }

    public static class Customer {
        private final Address address;
        public Customer(Address address) { this.address = address; }
        public Address getAddress() { return address; }
    }

    // BAD: caller must know about Customer → Address → city chain
    public static class OrderBad {
        private final Customer customer;
        public OrderBad(Customer customer) { this.customer = customer; }
        public Customer getCustomer() { return customer; }
    }

    // GOOD: Order hides the chain and exposes only what callers need
    public static class Order {
        private final Customer customer;

        public Order(Customer customer) { this.customer = customer; }

        // Delegate — caller doesn't need to know about Address at all
        public String getCity() {
            return customer.getAddress().getCity();
        }
    }
}
