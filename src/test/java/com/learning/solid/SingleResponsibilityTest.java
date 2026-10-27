package com.learning.solid;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SingleResponsibilityTest {

    @Test
    void orderHoldsData() {
        SingleResponsibility.Order order = new SingleResponsibility.Order(1, 49.99);
        assertEquals(1, order.getId());
        assertEquals(49.99, order.getTotal());
    }

    @Test
    void notifierProducesMessage() {
        SingleResponsibility.Order order = new SingleResponsibility.Order(42, 100.0);
        SingleResponsibility.OrderNotifier notifier = new SingleResponsibility.OrderNotifier();
        assertEquals("Order #42 confirmed. Total: $100.0", notifier.notify(order));
    }
}
