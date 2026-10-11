package oop.lab1.serbin.app;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import oop.lab1.serbin.io.CsvVegetableReader;
import oop.lab1.serbin.io.PropertiesSaladConfigReader;
import oop.lab1.serbin.model.Vegetable;
import oop.lab1.serbin.service.SaladConfig;
import oop.lab1.serbin.service.SaladService;

public class Main {

    private static final String VEGETABLES_RESOURCE = "/salad.csv";
    private static final String CONFIG_RESOURCE = "/salad.properties";

    public static void main(String[] args) throws IOException {
        SaladService service = loadService();
        printReport(service);
    }

    private static SaladService loadService() throws IOException {
        List<Vegetable> vegetables;
        try (InputStream stream = Main.class.getResourceAsStream(VEGETABLES_RESOURCE)) {
            if (stream == null) {
                throw new IOException("Resource not found: " + VEGETABLES_RESOURCE);
            }
            vegetables = new CsvVegetableReader().read(stream);
        }

        SaladConfig config;
        try (InputStream stream = Main.class.getResourceAsStream(CONFIG_RESOURCE)) {
            if (stream == null) {
                throw new IOException("Resource not found: " + CONFIG_RESOURCE);
            }
            config = new PropertiesSaladConfigReader().read(stream);
        }

        return new SaladService(config, vegetables);
    }

    private static void printReport(SaladService service) {
        SaladConfig config = service.getConfig();
        System.out.println("Salad: " + config.getSaladName());
        System.out.printf("Total calories: %.2f kcal%n", service.getTotalCalories());

        System.out.println("Sorted vegetables:");
        for (Vegetable vegetable : service.getSortedVegetables()) {
            System.out.println("  " + vegetable);
        }

        System.out.printf("Vegetables in calorie range %.0f..%.0f kcal/100g:%n",
                config.getCalorieRangeMin(), config.getCalorieRangeMax());
        for (Vegetable vegetable : service.findVegetablesInRange()) {
            System.out.println("  " + vegetable);
        }
    }
}