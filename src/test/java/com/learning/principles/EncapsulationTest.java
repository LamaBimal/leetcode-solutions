package com.learning.principles;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EncapsulationTest {

    @Test
    void rejectsNonfiniteTemperaturesAtConstructionAndUpdate() {
        Encapsulation.Thermometer thermometer = new Encapsulation.Thermometer(25);
        for (double value : new double[]{Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}) {
            assertThrows(IllegalArgumentException.class, () -> new Encapsulation.Thermometer(value));
            assertThrows(IllegalArgumentException.class, () -> thermometer.setTemperature(value));
            assertEquals(25.0, thermometer.getTemperature());
        }
    }

    @Test
    void acceptsAbsoluteZeroAndRejectsTheNextLowerValue() {
        Encapsulation.Thermometer thermometer = new Encapsulation.Thermometer(-273.15);
        assertEquals(-273.15, thermometer.getTemperature());
        assertThrows(IllegalArgumentException.class,
                () -> thermometer.setTemperature(Math.nextDown(-273.15)));
        assertEquals(-273.15, thermometer.getTemperature());
    }

    @Test
    void validTemperatureIsStored() {
        Encapsulation.Thermometer t = new Encapsulation.Thermometer(25.0);
        assertEquals(25.0, t.getTemperature(), 1e-9);
    }

    @Test
    void belowAbsoluteZeroThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new Encapsulation.Thermometer(-300.0));
    }

    @Test
    void setTemperatureEnforcesInvariant() {
        Encapsulation.Thermometer t = new Encapsulation.Thermometer(0.0);
        assertThrows(IllegalArgumentException.class,
                () -> t.setTemperature(-274.0));
    }
}
