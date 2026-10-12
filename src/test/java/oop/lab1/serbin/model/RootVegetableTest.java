package oop.lab1.serbin.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RootVegetableTest {

    private static final double DELTA = 1e-9;

    @Test
    void getCategory() {
        assertEquals("Root", new RootVegetable("Carrot", 100.0, 41.0, 15.0).getCategory());
    }

    @Test
    void constructorRejectsNegativeDepth() {
        assertThrows(IllegalArgumentException.class,
                () -> new RootVegetable("Carrot", 100.0, 41.0, -1.0));
    }

    @Test
    void setterRejectsNegativeDepth() {
        RootVegetable vegetable = new RootVegetable("Carrot", 100.0, 41.0, 15.0);
        assertThrows(IllegalArgumentException.class, () -> vegetable.setRootDepthCm(-2.0));
    }

    @Test
    void rootDepthGetterSetter() {
        RootVegetable vegetable = new RootVegetable("Carrot", 100.0, 41.0, 15.0);
        assertEquals(15.0, vegetable.getRootDepthCm(), DELTA);
        vegetable.setRootDepthCm(20.0);
        assertEquals(20.0, vegetable.getRootDepthCm(), DELTA);
    }

    @Test
    void toStringContainsRootDepth() {
        assertTrue(new RootVegetable("Beetroot", 100.0, 43.0, 20.0)
                .toString().contains("root depth: 20.0"));
    }
}