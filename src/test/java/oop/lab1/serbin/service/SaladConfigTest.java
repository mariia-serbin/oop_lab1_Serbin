package oop.lab1.serbin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class SaladConfigTest {

    private static final double DELTA = 1e-9;

    @Test
    void getters() {
        SaladConfig config = new SaladConfig("Test", "CALORIES", 10.0, 30.0);
        assertEquals("Test", config.getSaladName());
        assertEquals("CALORIES", config.getSortParameter());
        assertEquals(10.0, config.getCalorieRangeMin(), DELTA);
        assertEquals(30.0, config.getCalorieRangeMax(), DELTA);
    }

    @Test
    void rejectsBlankName() {
        assertThrows(IllegalArgumentException.class,
                () -> new SaladConfig("  ", "CALORIES", 10.0, 30.0));
    }

    @Test
    void rejectsBlankSortParameter() {
        assertThrows(IllegalArgumentException.class,
                () -> new SaladConfig("Test", "", 10.0, 30.0));
    }

    @Test
    void rejectsMinGreaterThanMax() {
        assertThrows(IllegalArgumentException.class,
                () -> new SaladConfig("Test", "CALORIES", 30.0, 10.0));
    }
}