package oop.lab1.serbin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;

import oop.lab1.serbin.model.FruitingVegetable;
import oop.lab1.serbin.model.LeafyVegetable;
import oop.lab1.serbin.model.RootVegetable;
import oop.lab1.serbin.model.Vegetable;

class SaladTest {

    private static final double DELTA = 1e-9;

    private Salad saladWithVegetables() {
        Salad salad = new Salad("Test Salad");
        salad.addVegetable(new LeafyVegetable("Lettuce", 150.0, 15.0, true));
        salad.addVegetable(new RootVegetable("Carrot", 120.0, 41.0, 15.0));
        salad.addVegetable(new FruitingVegetable("Tomato", 130.0, 20.0, true));
        return salad;
    }

    @Test
    void rejectsBlankName() {
        assertThrows(IllegalArgumentException.class, () -> new Salad("  "));
    }

    @Test
    void addNullVegetableRejected() {
        Salad salad = new Salad("Test");
        assertThrows(IllegalArgumentException.class, () -> salad.addVegetable(null));
    }

    @Test
    void removeVegetable() {
        Salad salad = saladWithVegetables();
        Vegetable vegetable = salad.getVegetables().get(0);
        assertTrue(salad.removeVegetable(vegetable));
        assertFalse(salad.removeVegetable(vegetable));
    }

    @Test
    void getVegetablesIsUnmodifiable() {
        Salad salad = saladWithVegetables();
        assertThrows(UnsupportedOperationException.class,
                () -> salad.getVegetables().add(new LeafyVegetable("X", 10.0, 1.0, false)));
    }

    @Test
    void totalCaloriesSumsPortions() {
        Salad salad = saladWithVegetables();
        assertEquals(22.5 + 49.2 + 26.0, salad.getTotalCalories(), 1e-6);
    }

    @Test
    void sortByCaloriesDoesNotChangeOriginalOrder() {
        Salad salad = saladWithVegetables();
        List<Vegetable> sorted = salad.sortByCalories();
        assertEquals(15.0, sorted.get(0).getCaloriesPer100g(), DELTA);
        assertEquals("Lettuce", salad.getVegetables().get(0).getName());
    }

    @Test
    void sortByWeightReturnsAscending() {
        Salad salad = saladWithVegetables();
        List<Vegetable> sorted = salad.sortByWeight();
        assertEquals(120.0, sorted.get(0).getWeightGrams(), DELTA);
        assertEquals(150.0, sorted.get(2).getWeightGrams(), DELTA);
    }

    @Test
    void findByCalorieRangeIsInclusive() {
        Salad salad = saladWithVegetables();
        List<Vegetable> found = salad.findByCalorieRange(15.0, 20.0);
        assertEquals(2, found.size());
    }

    @Test
    void findByCalorieRangeRejectsInvalidRange() {
        Salad salad = saladWithVegetables();
        assertThrows(IllegalArgumentException.class, () -> salad.findByCalorieRange(30.0, 10.0));
    }

    @Test
    void findByCalorieRangeEmptyResult() {
        Salad salad = saladWithVegetables();
        assertTrue(salad.findByCalorieRange(100.0, 200.0).isEmpty());
    }

    @Test
    void totalCaloriesUsesMockedVegetable() {
        Vegetable vegetable = mock(Vegetable.class);
        when(vegetable.getCalories()).thenReturn(42.0);
        Salad salad = new Salad("Mocked");
        salad.addVegetable(vegetable);
        assertEquals(42.0, salad.getTotalCalories(), DELTA);
        verify(vegetable).getCalories();
    }
}