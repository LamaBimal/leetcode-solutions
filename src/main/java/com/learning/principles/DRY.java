package com.learning.principles;

/**
 * DRY - Don't Repeat Yourself
 *
 * Every piece of knowledge should have a single, unambiguous representation.
 *
 * BAD: discount logic copy-pasted across multiple methods.
 * GOOD: extract into one shared method — change it once, applies everywhere.
 */
public class DRY {

    // BAD: duplicated discount logic
    public double priceForGold(double price) {
        return price - (price * 0.20); // 20% off — repeated elsewhere
    }

    public double priceForSilver(double price) {
        return price - (price * 0.20); // same logic, copy-pasted
    }

    // GOOD: single source of truth
    public double applyDiscount(double price, double rate) {
        return price - (price * rate);
    }

    public double goldPrice(double price)   { return applyDiscount(price, 0.20); }
    public double silverPrice(double price) { return applyDiscount(price, 0.10); }
}
