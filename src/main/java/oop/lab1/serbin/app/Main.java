package oop.lab1.serbin.app;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

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
        runMenu(service, new Scanner(System.in));
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

        printVegetables("Sorted vegetables:", service.getSortedVegetables());

        printVegetables(String.format("Vegetables in calorie range %.0f..%.0f kcal/100g:",
                config.getCalorieRangeMin(), config.getCalorieRangeMax()),
                service.findVegetablesInRange());
        System.out.println();
    }

    private static void runMenu(SaladService service, Scanner scanner) {
        boolean running = true;
        while (running) {
            printMenu();
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1":
                    System.out.printf("Total calories: %.2f kcal%n", service.getTotalCalories());
                    break;
                case "2":
                    printVegetables("Sorted vegetables:", service.getSortedVegetables());
                    break;
                case "3":
                    SaladConfig config = service.getConfig();
                    printVegetables(String.format(
                            "Vegetables in calorie range %.0f..%.0f kcal/100g:",
                            config.getCalorieRangeMin(), config.getCalorieRangeMax()),
                            service.findVegetablesInRange());
                    break;
                case "0":
                    running = false;
                    System.out.println("Bye");
                    break;
                default:
                    System.out.println("Unknown option: " + input);
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("1 - total calories");
        System.out.println("2 - sorted vegetables");
        System.out.println("3 - vegetables in calorie range");
        System.out.println("0 - exit");
        System.out.print("Your choice: ");
    }

    private static void printVegetables(String title, List<Vegetable> vegetables) {
        System.out.println(title);
        for (Vegetable vegetable : vegetables) {
            System.out.println("  " + vegetable);
        }
    }
}