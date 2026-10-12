package oop.lab1.serbin.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class FruitingVegetableTest {

    @Test
    void getCategory() {
        assertEquals("Fruiting", new FruitingVegetable("Tomato", 100.0, 20.0, true).getCategory());
    }

    @Test
    void ripeGetterSetter() {
        FruitingVegetable vegetable = new FruitingVegetable("Tomato", 100.0, 20.0, true);
        assertTrue(vegetable.isRipe());
        vegetable.setRipe(false);
        assertFalse(vegetable.isRipe());
    }

    @Test
    void toStringContainsRipe() {
        assertTrue(new FruitingVegetable("Cucumber", 100.0, 16.0, false)
                .toString().contains("ripe: false"));
    }
}