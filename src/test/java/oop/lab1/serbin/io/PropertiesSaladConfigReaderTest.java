package oop.lab1.serbin.io;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import oop.lab1.serbin.service.SaladConfig;

class PropertiesSaladConfigReaderTest {

    private static final double DELTA = 1e-9;

    private final PropertiesSaladConfigReader reader = new PropertiesSaladConfigReader();

    @Test
    void readsConfig() throws IOException {
        String content = "salad.name=Vitamin Salad\n"
                + "salad.sort.parameter=CALORIES\n"
                + "salad.calorie.range.min=15\n"
                + "salad.calorie.range.max=30\n";
        SaladConfig config = reader.read(
                new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)));
        assertEquals("Vitamin Salad", config.getSaladName());
        assertEquals("CALORIES", config.getSortParameter());
        assertEquals(15.0, config.getCalorieRangeMin(), DELTA);
        assertEquals(30.0, config.getCalorieRangeMax(), DELTA);
    }
}