package com.learning.principles;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DRYTest {

    private final DRY dry = new DRY();

    @Test
    void applyDiscountCalculatesCorrectly() {
        assertEquals(80.0, dry.applyDiscount(100.0, 0.20), 1e-9);
    }

    @Test
    void goldPriceUses20PercentDiscount() {
        assertEquals(80.0, dry.goldPrice(100.0), 1e-9);
    }

    @Test
    void silverPriceUses10PercentDiscount() {
        assertEquals(90.0, dry.silverPrice(100.0), 1e-9);
    }
}
