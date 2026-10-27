package com.learning.principles;

/**
 * Encapsulation / Information Hiding
 *
 * Expose only what is necessary; hide internal implementation details.
 * Callers depend on the public interface, not the internals.
 *
 * BAD: public fields let anyone mutate state directly, breaking invariants.
 * GOOD: private fields with controlled access via methods that enforce rules.
 */
public class Encapsulation {

    // BAD: public fields — anyone can set temperature to -9999
    public static class ThermometerBad {
        public double temperature; // no protection
    }

    // GOOD: internal state hidden, invariant enforced in one place
    public static class Thermometer {
        private static final double MIN = -273.15; // absolute zero
        private double temperature;

        public Thermometer(double temperature) {
            setTemperature(temperature);
        }

        public double getTemperature() { return temperature; }

        public void setTemperature(double temperature) {
            if (temperature < MIN) {
                throw new IllegalArgumentException("Temperature below absolute zero");
            }
            this.temperature = temperature;
        }

        public String unit() { return "Celsius"; }
    }
}
