package oop.lab1.serbin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;

import oop.lab1.serbin.model.FruitingVegetable;
import oop.lab1.serbin.model.LeafyVegetable;
import oop.lab1.serbin.model.RootVegetable;
import oop.lab1.serbin.model.Vegetable;

class SaladServiceTest {

    private static final double DELTA = 1e-9;

    private List<Vegetable> vegetables() {
        return List.of(
                new LeafyVegetable("Lettuce", 150.0, 15.0, true),
                new RootVegetable("Carrot", 120.0, 41.0, 15.0),
                new FruitingVegetable("Tomato", 130.0, 20.0, true));
    }

    private SaladConfig config(String sort, double min, double max) {
        SaladConfig config = mock(SaladConfig.class);
        when(config.getSaladName()).thenReturn("Mock Salad");
        when(config.getSortParameter()).thenReturn(sort);
        when(config.getCalorieRangeMin()).thenReturn(min);
        when(config.getCalorieRangeMax()).thenReturn(max);
        return config;
    }

    @Test
    void constructorRejectsNullConfig() {
        assertThrows(IllegalArgumentException.class, () -> new SaladService(null, vegetables()));
    }

    @Test
    void constructorRejectsNullVegetables() {
        assertThrows(IllegalArgumentException.class,
                () -> new SaladService(config("CALORIES", 0, 100), null));
    }

    @Test
    void buildsSaladWithConfigName() {
        SaladService service = new SaladService(config("CALORIES", 0, 100), vegetables());
        assertEquals("Mock Salad", service.getSalad().getName());
        assertEquals(3, service.getSalad().getVegetables().size());
    }

    @Test
    void totalCalories() {
        SaladService service = new SaladService(config("CALORIES", 0, 100), vegetables());
        assertEquals(22.5 + 49.2 + 26.0, service.getTotalCalories(), 1e-6);
    }

    @Test
    void sortedByCalories() {
        SaladService service = new SaladService(config("CALORIES", 0, 100), vegetables());
        List<Vegetable> sorted = service.getSortedVegetables();
        assertEquals(15.0, sorted.get(0).getCaloriesPer100g(), DELTA);
    }

    @Test
    void sortedByWeight() {
        SaladService service = new SaladService(config("weight", 0, 100), vegetables());
        List<Vegetable> sorted = service.getSortedVegetables();
        assertEquals(120.0, sorted.get(0).getWeightGrams(), DELTA);
    }

    @Test
    void unknownSortParameterRejected() {
        SaladService service = new SaladService(config("COLOR", 0, 100), vegetables());
        assertThrows(IllegalArgumentException.class, service::getSortedVegetables);
    }

    @Test
    void findVegetablesInRange() {
        SaladService service = new SaladService(config("CALORIES", 15.0, 20.0), vegetables());
        List<Vegetable> found = service.findVegetablesInRange();
        assertEquals(2, found.size());
        assertTrue(found.stream().allMatch(v -> v.getCaloriesPer100g() >= 15.0));
    }

    @Test
    void getConfigReturnsSameInstance() {
        SaladConfig config = config("CALORIES", 0, 100);
        SaladService service = new SaladService(config, vegetables());
        assertEquals(config, service.getConfig());
    }
}