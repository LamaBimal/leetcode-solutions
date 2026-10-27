package com.learning.principles;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class YAGNITest {

    @Test
    void saveAndFindUser() {
        YAGNI.UserService service = new YAGNI.UserService();
        YAGNI.User user = new YAGNI.User(1, "Alice");
        service.save(user);
        assertEquals("Alice", service.findById(1).getName());
    }

    @Test
    void findByIdReturnsNullWhenNotFound() {
        YAGNI.UserService service = new YAGNI.UserService();
        assertNull(service.findById(99));
    }
}
