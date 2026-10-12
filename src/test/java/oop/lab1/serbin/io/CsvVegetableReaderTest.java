package oop.lab1.serbin.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.jupiter.api.Test;

import oop.lab1.serbin.model.FruitingVegetable;
import oop.lab1.serbin.model.LeafyVegetable;
import oop.lab1.serbin.model.RootVegetable;
import oop.lab1.serbin.model.Vegetable;

class CsvVegetableReaderTest {

    private static final double DELTA = 1e-9;

    private final CsvVegetableReader reader = new CsvVegetableReader();

    private List<Vegetable> read(String csv) throws IOException {
        return reader.read(new ByteArrayInputStream(csv.getBytes(StandardCharsets.UTF_8)));
    }

    @Test
    void readsAllTypes() throws IOException {
        String csv = "LEAFY;Lettuce;150;15;true\n"
                + "ROOT;Carrot;120;41;15\n"
                + "FRUITING;Tomato;130;20;true\n";
        List<Vegetable> vegetables = read(csv);
        assertEquals(3, vegetables.size());
        assertInstanceOf(LeafyVegetable.class, vegetables.get(0));
        assertInstanceOf(RootVegetable.class, vegetables.get(1));
        assertInstanceOf(FruitingVegetable.class, vegetables.get(2));
        assertEquals("Lettuce", vegetables.get(0).getName());
        assertEquals(150.0, vegetables.get(0).getWeightGrams(), DELTA);
        assertEquals(15.0, vegetables.get(0).getCaloriesPer100g(), DELTA);
        assertTrue(((LeafyVegetable) vegetables.get(0)).isGreenhouseGrown());
        assertEquals(15.0, ((RootVegetable) vegetables.get(1)).getRootDepthCm(), DELTA);
    }

    @Test
    void skipsBlankLinesAndComments() throws IOException {
        String csv = "# header\n\nLEAFY;Lettuce;150;15;true\n\n";
        assertEquals(1, read(csv).size());
    }

    @Test
    void rootWithoutExtraDefaultsToZeroDepth() throws IOException {
        List<Vegetable> vegetables = read("ROOT;Carrot;120;41\n");
        assertEquals(0.0, ((RootVegetable) vegetables.get(0)).getRootDepthCm(), DELTA);
    }

    @Test
    void rejectsTooFewFields() {
        assertThrows(IllegalArgumentException.class, () -> read("LEAFY;Lettuce;150\n"));
    }

    @Test
    void rejectsUnknownType() {
        assertThrows(IllegalArgumentException.class, () -> read("BERRY;Strawberry;100;30;true\n"));
    }

    @Test
    void rejectsNonNumericNumbers() {
        assertThrows(NumberFormatException.class, () -> read("LEAFY;Lettuce;abc;15;true\n"));
    }
}