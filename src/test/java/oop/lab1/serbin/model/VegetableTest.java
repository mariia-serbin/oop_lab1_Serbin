package oop.lab1.serbin.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class VegetableTest {

    private static final double DELTA = 1e-9;

    private LeafyVegetable vegetable() {
        return new LeafyVegetable("Lettuce", 200.0, 15.0, false);
    }

    @Test
    void getCaloriesCalculatesPortion() {
        assertEquals(30.0, vegetable().getCalories(), DELTA);
    }

    @Test
    void constructorRejectsNullName() {
        assertThrows(IllegalArgumentException.class,
                () -> new LeafyVegetable(null, 100.0, 10.0, false));
    }

    @Test
    void constructorRejectsBlankName() {
        assertThrows(IllegalArgumentException.class,
                () -> new LeafyVegetable("  ", 100.0, 10.0, false));
    }

    @Test
    void constructorRejectsNonPositiveWeight() {
        assertThrows(IllegalArgumentException.class,
                () -> new LeafyVegetable("Lettuce", 0.0, 10.0, false));
    }

    @Test
    void constructorRejectsNegativeCalories() {
        assertThrows(IllegalArgumentException.class,
                () -> new LeafyVegetable("Lettuce", 100.0, -1.0, false));
    }

    @Test
    void setWeightRejectsNonPositive() {
        LeafyVegetable vegetable = vegetable();
        assertThrows(IllegalArgumentException.class, () -> vegetable.setWeightGrams(0));
    }

    @Test
    void setWeightUpdatesCalories() {
        LeafyVegetable vegetable = vegetable();
        vegetable.setWeightGrams(100.0);
        assertEquals(100.0, vegetable.getWeightGrams(), DELTA);
        assertEquals(15.0, vegetable.getCalories(), DELTA);
    }

    @Test
    void gettersReturnValues() {
        LeafyVegetable vegetable = vegetable();
        assertEquals("Lettuce", vegetable.getName());
        assertEquals(15.0, vegetable.getCaloriesPer100g(), DELTA);
    }

    @Test
    void compareToOrdersByCalories() {
        LeafyVegetable light = new LeafyVegetable("Light", 100.0, 10.0, false);
        LeafyVegetable heavy = new LeafyVegetable("Heavy", 100.0, 50.0, false);
        assertTrue(light.compareTo(heavy) < 0);
        assertTrue(heavy.compareTo(light) > 0);
        assertEquals(0, light.compareTo(new LeafyVegetable("Other", 100.0, 10.0, true)));
    }

    @Test
    void toStringContainsCategoryAndName() {
        String result = vegetable().toString();
        assertTrue(result.contains("Leafy"));
        assertTrue(result.contains("Lettuce"));
    }
}