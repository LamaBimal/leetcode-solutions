package com.learning.principles;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LawOfDemeterTest {

    @Test
    void orderExposesCity() {
        LawOfDemeter.Address address   = new LawOfDemeter.Address("Berlin");
        LawOfDemeter.Customer customer = new LawOfDemeter.Customer(address);
        LawOfDemeter.Order order       = new LawOfDemeter.Order(customer);

        // Caller only talks to Order — no chain traversal
        assertEquals("Berlin", order.getCity());
    }

    @Test
    void badOrderRequiresChainTraversal() {
        LawOfDemeter.Address address   = new LawOfDemeter.Address("Paris");
        LawOfDemeter.Customer customer = new LawOfDemeter.Customer(address);
        LawOfDemeter.OrderBad order    = new LawOfDemeter.OrderBad(customer);

        // Violates LoD — caller must know the internal structure
        assertEquals("Paris", order.getCustomer().getAddress().getCity());
    }
}
