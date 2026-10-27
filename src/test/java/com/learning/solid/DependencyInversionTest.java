package com.learning.solid;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DependencyInversionTest {

    @Test
    void orderServiceWorksWithInMemoryDb() {
        DependencyInversion.InMemoryDatabase db = new DependencyInversion.InMemoryDatabase();
        DependencyInversion.OrderService service = new DependencyInversion.OrderService(db);

        service.placeOrder("order-1");
        assertEquals("order-1", service.getOrder("order-1"));
    }

    @Test
    void orderServiceWorksWithAnyDatabase() {
        // Swap to a different impl without changing OrderService
        DependencyInversion.Database mockDb = new DependencyInversion.Database() {
            @Override public void save(String data) {}
            @Override public String load(String id) { return "mocked-" + id; }
        };

        DependencyInversion.OrderService service = new DependencyInversion.OrderService(mockDb);
        assertEquals("mocked-42", service.getOrder("42"));
    }
}
