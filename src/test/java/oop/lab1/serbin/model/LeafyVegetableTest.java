package oop.lab1.serbin.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class LeafyVegetableTest {

    @Test
    void getCategory() {
        assertEquals("Leafy", new LeafyVegetable("Lettuce", 100.0, 15.0, false).getCategory());
    }

    @Test
    void greenhouseGrownGetterSetter() {
        LeafyVegetable vegetable = new LeafyVegetable("Spinach", 100.0, 23.0, true);
        assertTrue(vegetable.isGreenhouseGrown());
        vegetable.setGreenhouseGrown(false);
        assertFalse(vegetable.isGreenhouseGrown());
    }

    @Test
    void toStringContainsGreenhouse() {
        assertTrue(new LeafyVegetable("Lettuce", 100.0, 15.0, true)
                .toString().contains("greenhouse: true"));
    }
}