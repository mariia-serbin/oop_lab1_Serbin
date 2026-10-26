package oop.lab1.serbin.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import oop.lab1.serbin.model.FruitingVegetable;
import oop.lab1.serbin.model.LeafyVegetable;
import oop.lab1.serbin.model.RootVegetable;
import oop.lab1.serbin.model.Vegetable;

/**
 * Reads vegetables from CSV data. Each non-empty line that does not start with
 * {@code #} has the format {@code type;name;weightGrams;caloriesPer100g;extra}.
 */
public class CsvVegetableReader implements VegetableReader {

    private static final String SEPARATOR = ";";

    @Override
    public List<Vegetable> read(InputStream inputStream) throws IOException {
        List<Vegetable> vegetables = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                String trimmed = line.trim();
                if (trimmed.isEmpty() || trimmed.startsWith("#")) {
                    continue;
                }
                vegetables.add(parseLine(trimmed, lineNumber));
            }
        }
        return vegetables;
    }

    private Vegetable parseLine(String line, int lineNumber) {
        String[] parts = line.split(SEPARATOR, -1);
        if (parts.length < 4) {
            throw new IllegalArgumentException("Invalid line " + lineNumber + ": " + line);
        }
        String type = parts[0].trim();
        String name = parts[1].trim();
        double weightGrams = Double.parseDouble(parts[2].trim());
        double caloriesPer100g = Double.parseDouble(parts[3].trim());
        String extra = parts.length > 4 ? parts[4].trim() : "";
        switch (type.toUpperCase()) {
            case "LEAFY":
                return new LeafyVegetable(name, weightGrams, caloriesPer100g,
                        Boolean.parseBoolean(extra));
            case "ROOT":
                double rootDepthCm = extra.isEmpty() ? 0 : Double.parseDouble(extra);
                return new RootVegetable(name, weightGrams, caloriesPer100g, rootDepthCm);
            case "FRUITING":
                return new FruitingVegetable(name, weightGrams, caloriesPer100g,
                        Boolean.parseBoolean(extra));
            default:
                throw new IllegalArgumentException(
                        "Unknown vegetable type at line " + lineNumber + ": " + type);
        }
    }
}