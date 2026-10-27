package com.learning.principles;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EncapsulationTest {

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
